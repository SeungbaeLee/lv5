package com.hh99.lv5.domain.bucket.controller;

import com.hh99.lv5.domain.bucket.dto.BucketResponseDto;
import com.hh99.lv5.domain.bucket.service.BucketService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members/{memberId}/buckets")
public class BucketController {

    private final BucketService bucketService;

    @GetMapping
    public ResponseEntity readBucket(@PathVariable("memberId") @Positive long memberId) {
        BucketResponseDto bucketResponseDto = bucketService.readBucket(memberId);
        return new ResponseEntity<>(bucketResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity updateQuantity(@PathVariable("memberId") @Positive long memberId,
                                         @PathVariable("productId") @Positive long productId,
                                         @RequestParam long quantity) {
        BucketResponseDto bucketResponseDto = bucketService.updateQuantity(memberId, productId, quantity);
        return new ResponseEntity<>(bucketResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProductFromBucket(@PathVariable("memberId") @Positive long memberId,
                                                  @PathVariable("productId") @Positive long productId) {
        bucketService.deleteProductFromBucket(memberId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
