package com.gamul.gamul.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmark")
public class TestController {

    @GetMapping("")
    public ResponseEntity<String> reissue(@RequestBody String market) {
        return ResponseEntity.ok(market);
    }
}
