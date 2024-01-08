package com.hh99.lv5.domain.bucket.service;

import com.hh99.lv5.domain.bucket.dto.BucketResponseDto;
import com.hh99.lv5.domain.bucket.entity.Bucket;
import com.hh99.lv5.domain.bucket.repository.BucketRepository;
import com.hh99.lv5.domain.bucketProduct.entity.BucketProduct;
import com.hh99.lv5.domain.bucketProduct.repository.BucketProductRepository;
import com.hh99.lv5.domain.member.entity.Member;
import com.hh99.lv5.domain.member.service.MemberService;
import com.hh99.lv5.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BucketService {

    private final BucketRepository bucketRepository;
    private final BucketProductRepository bucketProductRepository;
    private final MemberService memberService;

    public void addProductToBucket(Product product, Member member, long quantity) {
//        Bucket bucket = bucketRepository.findByMemberId(member.getMemberId())
//                .orElseGet(()->{
//                    Bucket newBucket = Bucket.builder()
//                            .member(member)
//                            .build();
//                    return bucketRepository.save(newBucket);
//                });
        Bucket bucket = getBucketByMember(member);
        BucketProduct existingProduct = findProductInBucket(bucket, product);

        if (existingProduct != null) {
            existingProduct.updateCount(quantity);
        } else {
            BucketProduct bucketProduct = BucketProduct.builder()
                    .product(product)
                    .count(quantity)
                    .bucket(bucket)
                    .build();
            bucket.getBucketProducts().add(bucketProduct);
            bucketProductRepository.save(bucketProduct);
        }
    }
    @Transactional(readOnly = true)
    public BucketResponseDto readBucket(long memberId) {
        Member member = memberService.findMemberById(memberId);
        Bucket bucket = getBucketByMember(member);

        List<BucketProduct> bucketProducts = bucket.getBucketProducts();
        return BucketResponseDto.builder()
                .bucketProducts(bucketProducts)
                .totalPrice(calculateTotalPrice(bucketProducts))
                .build();
    }

    public BucketResponseDto updateQuantity(long memberId, long productId, long quantity) {
        Member member = memberService.findMemberById(memberId);
        Bucket bucket = getBucketByMember(member);

        BucketProduct bucketProduct = findBucketProductByProductId(bucket.getBucketId(), productId);
        bucketProduct.updateCount(quantity);

        List<BucketProduct> bucketProducts = bucket.getBucketProducts();
        return BucketResponseDto.builder()
                .bucketProducts(bucketProducts)
                .totalPrice(calculateTotalPrice(bucketProducts))
                .build();

    }

    public void deleteProductFromBucket(long memberId, long productId) {
        Member member = memberService.findMemberById(memberId);
        Bucket bucket = getBucketByMember(member);

        BucketProduct bucketProduct = findBucketProductByProductId(bucket.getBucketId(), productId);
        bucketProductRepository.delete(bucketProduct);
    }

    @Transactional(readOnly = true)
    private Bucket getBucketByMember(Member member) {
        Bucket bucket = bucketRepository.findByMember_MemberId(member.getMemberId())
                .orElseGet(()->{
                    Bucket newBucket = Bucket.builder()
                            .member(member)
                            .build();
                    return bucketRepository.save(newBucket);
                });
        return bucket;
    }

    @Transactional(readOnly = true)
    private BucketProduct findProductInBucket(Bucket bucket, Product product) {
        return bucket.getBucketProducts().stream()
                .filter(bp -> bp.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    private Long calculateTotalPrice(List<BucketProduct> bucketProducts) {
        return bucketProducts.stream()
                .mapToLong(bP -> bP.getProduct().getPrice() * bP.getCount())
                .sum();
    }

    private BucketProduct findBucketProductByProductId(Long bucketId, Long productId) {
        // 장바구니에서 해당 상품을 찾아 반환합니다.
        return bucketProductRepository.findBucketProductByBucketAndProductId(bucketId, productId)
                .orElse(null);
    }
}
