package com.hh99.lv5.domain.member.controller;

import com.hh99.lv5.domain.member.dto.MemberPostDto;
import com.hh99.lv5.domain.member.dto.MemberResponseDto;
import com.hh99.lv5.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(@Valid @RequestBody MemberPostDto postDto) {
        MemberResponseDto responseDto = memberService.createMember(postDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
