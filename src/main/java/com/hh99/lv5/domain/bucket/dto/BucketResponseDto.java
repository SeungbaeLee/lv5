package com.hh99.lv5.domain.bucket.dto;

import com.hh99.lv5.domain.bucketProduct.entity.BucketProduct;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BucketResponseDto {

    private List<BucketProduct> bucketProducts;
    private Long totalPrice;

    @Builder
    public BucketResponseDto(List<BucketProduct> bucketProducts, Long totalPrice) {
        this.bucketProducts = bucketProducts;
        this.totalPrice = totalPrice;
    }

}
