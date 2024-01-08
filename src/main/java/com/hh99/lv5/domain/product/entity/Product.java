package com.hh99.lv5.domain.product.entity;

import com.hh99.lv5.domain.bucketProduct.entity.BucketProduct;
import com.hh99.lv5.global.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private Long price;

    private Long quantity;

    private String introduction;

    private String category;

    @ElementCollection
    private List<String> imageUrl;

    @OneToMany(mappedBy = "product")
    private List<BucketProduct> bucketProducts = new ArrayList<>();

    @Builder
    public Product(Long productId, String productName, Long price, Long quantity, String introduction, String category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.introduction = introduction;
        this.category = category;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
