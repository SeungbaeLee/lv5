package com.hh99.lv5.domain.product.controller;

import com.hh99.lv5.domain.member.entity.Member;
import com.hh99.lv5.domain.product.dto.ProductPostDto;
import com.hh99.lv5.domain.product.dto.ProductResponseDto;
import com.hh99.lv5.domain.product.entity.Product;
import com.hh99.lv5.domain.product.service.ProductService;
import com.hh99.lv5.global.page.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.LimitExceededException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //create
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestPart @Valid ProductPostDto postDto,
                                                            @RequestPart List<MultipartFile> multipartFiles) {
        ProductResponseDto productResponseDto = productService.createProduct(postDto, multipartFiles);
        return new ResponseEntity<ProductResponseDto>(productResponseDto, HttpStatus.CREATED);
    }

    //readOne
    @GetMapping("{productId}")
    public ResponseEntity<ProductResponseDto> readProduct(@PathVariable @Positive long productId) {
        ProductResponseDto productResponseDto = productService.readProduct(productId);
        return new ResponseEntity<ProductResponseDto>(productResponseDto, HttpStatus.OK);
    }
    //readAll
    @GetMapping
    public ResponseEntity<PageDto> readProducts(Pageable pageable) {
        Page<Product> productPage = productService.readProducts(pageable);
        List<Product> productList = productPage.getContent();

        PageDto pageDto = new PageDto<>(ProductResponseDto.fromEntityList(productList), productPage);
        return new ResponseEntity<PageDto>(pageDto, HttpStatus.OK);
    }

    @PostMapping("/{productId}/buckets")
    public ResponseEntity<ProductResponseDto> addToBucket(@PathVariable("productId") @Positive long productId,
                                                          @RequestParam long quantity,
                                                          @AuthenticationPrincipal UserDetails member) {
        productService.addToBucket(productId, quantity, member);
        return new ResponseEntity<ProductResponseDto>(HttpStatus.OK);
    }
}
