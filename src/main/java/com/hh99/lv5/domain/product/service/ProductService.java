package com.hh99.lv5.domain.product.service;

import com.hh99.lv5.domain.product.dto.ProductPostDto;
import com.hh99.lv5.domain.product.dto.ProductResponseDto;
import com.hh99.lv5.domain.product.entity.Product;
import com.hh99.lv5.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    //create
    public ProductResponseDto createProduct(ProductPostDto postDto) {

        Product product = postDto.toEntity();
        Product savedProduct = productRepository.save(product);

        return ProductResponseDto.fromEntity(savedProduct);
    }

    //readOne
    public ProductResponseDto readProduct(long productId) {
        Product product = findProductById(productId);
        return ProductResponseDto.fromEntity(product);
    }


    //readAll
    public Page<Product> readProducts(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return productRepository.findAll(pageRequest);
    }

    //검증
    public Product findProductById(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new NullPointerException("찾을 수 없는 상품입니다."));
        return product;

    }
}
