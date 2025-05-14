package com.example.ratelimiter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    private String script;

    public boolean isAllowed(String key, int capacity, int refillRate) {
        try {
            if (script == null) {
                script = new String(new ClassPathResource("rate_limiter.lua").getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }

            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            redisScript.setResultType(Long.class);
            Long result = redisTemplate.execute(
                    redisScript,
                    Collections.singletonList(key),
                    String.valueOf(capacity),
                    String.valueOf(refillRate)
            );
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return true; // If Redis fails, allow request to not impact UX
        }
    }
}
