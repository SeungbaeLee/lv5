package com.hh99.lv5.domain.bucket.entity;

import com.hh99.lv5.domain.member.entity.Member;
import com.hh99.lv5.global.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
