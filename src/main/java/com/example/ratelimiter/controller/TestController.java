package com.example.ratelimiter.controller;

import com.example.ratelimiter.annotation.RateLimited;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class TestController {

    @RateLimited(capacity = 5, refillRate = 1)
    @GetMapping("hello")
    public String hello() {
        return "Hello, Rate Limited World!";
    }
}
