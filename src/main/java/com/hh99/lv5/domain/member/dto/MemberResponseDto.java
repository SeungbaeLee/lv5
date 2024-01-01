package com.hh99.lv5.domain.member.dto;

import com.hh99.lv5.domain.member.entity.Member;
import com.hh99.lv5.domain.member.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private long memberId;
    private String email;
    private String gender;
    private String phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public MemberResponseDto(Long memberId, String email, String gender, String phoneNumber, String address, Role role) {
        this.memberId = memberId;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .gender(member.getGender())
                .phoneNumber(member.getPhoneNumber())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }
}
