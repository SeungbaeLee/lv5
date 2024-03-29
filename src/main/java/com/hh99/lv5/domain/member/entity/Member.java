package com.hh99.lv5.domain.member.entity;

import com.hh99.lv5.global.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;

    private String password;

    private String gender;

    private String phoneNumber;

    private String address;

    private Role role;

//    private String refreshToken;

    @Builder
    public Member(Long memberId, String email, String password, String gender, String phoneNumber, String address, Role role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

//    public void updateRefreshToken(String updateRefreshToken) {
//        this.refreshToken = updateRefreshToken;
//    }
}
