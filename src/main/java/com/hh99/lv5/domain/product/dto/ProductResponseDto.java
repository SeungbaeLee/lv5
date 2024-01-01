package com.hh99.lv5.domain.product.dto;

import com.hh99.lv5.domain.member.dto.MemberResponseDto;
import com.hh99.lv5.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductResponseDto {

    private long productId;
    private String productName;
    private long price;
    private long quantity;
    private String introduction;
    private String category;

    @Builder
    public ProductResponseDto(long productId, String productName, long price, long quantity, String introduction, String category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.introduction = introduction;
        this.category = category;
    }

    public static ProductResponseDto fromEntity(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .introduction(product.getIntroduction())
                .category(product.getCategory())
                .build();
    }

    public static List<ProductResponseDto> fromEntityList(List<Product> productList) {
        return productList.stream()
                .map(ProductResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
