package com.hh99.lv5.domain.bucketProduct.entity;

import com.hh99.lv5.domain.bucket.entity.Bucket;
import com.hh99.lv5.domain.product.entity.Product;
import com.hh99.lv5.global.auditing.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BucketProduct extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bucketProductId;

    private Bucket bucket;

    private Product product;

    private Long count;
}
