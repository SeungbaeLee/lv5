package com.hh99.lv5.domain.bucketProduct.repository;

import com.hh99.lv5.domain.bucket.entity.Bucket;
import com.hh99.lv5.domain.bucketProduct.entity.BucketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BucketProductRepository extends JpaRepository<BucketProduct, Long> {
    @Query("SELECT bp FROM BucketProduct bp WHERE bp.bucket = :bucket AND bp.product.productId = :productId")
    Optional<BucketProduct> findBucketProductByBucketAndProductId(@Param("bucket") Bucket bucket, @Param("productId") Long productId);
}
