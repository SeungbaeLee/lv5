package com.hh99.lv5.domain.bucketProduct.entity;

import com.hh99.lv5.domain.bucket.entity.Bucket;
import com.hh99.lv5.domain.product.entity.Product;
import com.hh99.lv5.global.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BucketProduct extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bucketProductId;

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Long count;

    @Builder
    public BucketProduct(Long bucketProductId, Bucket bucket, Product product, Long count) {
        this.bucketProductId = bucketProductId;
        this.bucket = bucket;
        this.product = product;
        this.count = count;
    }

    public void updateCount(long quantity) {
        this.count = getCount()+quantity;
    }
}
