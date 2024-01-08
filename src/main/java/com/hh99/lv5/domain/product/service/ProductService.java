package com.hh99.lv5.domain.product.service;

import com.hh99.lv5.domain.bucket.entity.Bucket;
import com.hh99.lv5.domain.bucket.repository.BucketRepository;
import com.hh99.lv5.domain.bucket.service.BucketService;
import com.hh99.lv5.domain.member.entity.Member;
import com.hh99.lv5.domain.member.service.MemberService;
import com.hh99.lv5.domain.product.dto.ProductPostDto;
import com.hh99.lv5.domain.product.dto.ProductResponseDto;
import com.hh99.lv5.domain.product.entity.Product;
import com.hh99.lv5.domain.product.repository.ProductRepository;
import com.hh99.lv5.global.aws.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.LimitExceededException;
import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final BucketService bucketService;

    private final AwsS3Service awsS3Service;

    //create
    public ProductResponseDto createProduct(ProductPostDto postDto, List<MultipartFile> multipartFiles) {

        Product product = postDto.toEntity();
        List<String> imageUrl = awsS3Service.uploadImage(multipartFiles);
        product.setImageUrl(imageUrl);
        Product savedProduct = productRepository.save(product);


        return ProductResponseDto.fromEntity(savedProduct);
    }

    //readOne
    @Transactional(readOnly = true)
    public ProductResponseDto readProduct(long productId) {
        Product product = findProductById(productId);
        return ProductResponseDto.fromEntity(product);
    }


    //readAll
    @Transactional(readOnly = true)
    public Page<Product> readProducts(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return productRepository.findAll(pageRequest);
    }

    public void addToBucket(long productId, long quantity) {
        Product product = findProductById(productId);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("상품의 수량을 초과했습니다. 수량을 조절해주세요.");
        }
        bucketService.addProductToBucket(product, member, quantity);
    }


    //검증
    @Transactional(readOnly = true)
    public Product findProductById(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new NullPointerException("찾을 수 없는 상품입니다."));
        return product;

    }
}
