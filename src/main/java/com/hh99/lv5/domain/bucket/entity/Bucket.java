package com.hh99.lv5.domain.bucket.entity;

import com.hh99.lv5.domain.bucketProduct.entity.BucketProduct;
import com.hh99.lv5.domain.member.entity.Member;
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
public class Bucket extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bucketId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "bucket")
    private List<BucketProduct> bucketProducts = new ArrayList<>();

    @Builder
    public Bucket(Long bucketId, Member member) {
        this.bucketId = bucketId;
        this.member = member;
    }
}
