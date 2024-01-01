package com.hh99.lv5.domain.product.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.naming.LimitExceededException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //create
    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductPostDto postDto) {
        ProductResponseDto productResponseDto = productService.createProduct(postDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    //readOne
    @GetMapping("{productId}")
    public ResponseEntity readProduct(@PathVariable @Positive long productId) {
        ProductResponseDto productResponseDto = productService.readProduct(productId);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    //readAll
    @GetMapping
    public ResponseEntity readProducts(Pageable pageable) {
        Page<Product> productPage = productService.readProducts(pageable);
        List<Product> productList = productPage.getContent();

        PageDto pageDto = new PageDto<>(ProductResponseDto.fromEntityList(productList), productPage);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @PostMapping("/{productId}/buckets")
    public ResponseEntity addToBucket(@PathVariable("productId") @Positive long productId,
                                      @RequestParam long memberId,
                                      @RequestParam long quantity) {
        productService.addToBucket(productId, memberId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
