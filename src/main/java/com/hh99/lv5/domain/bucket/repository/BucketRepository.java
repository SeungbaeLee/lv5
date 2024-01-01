package com.hh99.lv5.domain.bucket.repository;

import com.hh99.lv5.domain.bucket.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    Optional<Bucket> findByMemberId(long memberId);
}
