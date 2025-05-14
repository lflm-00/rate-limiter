package com.example.ratelimiter.aspect;

import com.example.ratelimiter.annotation.RateLimited;
import com.example.ratelimiter.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterAspect {

    @Autowired
    private final RateLimiterService rateLimiterService;

    @Around("@annotation(rateLimited)")
    public Object limitRate(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String key = "rate_limiter:" + request.getRemoteAddr();

        boolean allowed = rateLimiterService.isAllowed(key, rateLimited.capacity(), rateLimited.refillRate());

        if (!allowed) {
            throw new RuntimeException("Too many requests. Please try again later.");
        }

        return joinPoint.proceed();
    }
}
