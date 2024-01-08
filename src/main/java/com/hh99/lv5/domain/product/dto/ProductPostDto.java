package com.hh99.lv5.domain.product.dto;

import com.hh99.lv5.domain.member.entity.Member;
import com.hh99.lv5.domain.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductPostDto {

    @NonNull
    private String productName;
    @NonNull
    private Long price;
    @NonNull
    private Long quantity;
    @NonNull
    private String introduction;
    @NonNull
    private String category;

    public Product toEntity() {
        return Product.builder()
                .productName(this.productName)
                .price(this.price)
                .quantity(this.quantity)
                .introduction(this.introduction)
                .category(this.category)
                .build();
    }
}
