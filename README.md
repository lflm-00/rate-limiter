# 🚀 Rate Limiting Microservice with Spring Boot, Redis, Lua & AOP

[![Build Status](https://img.shields.io/badge/Spring_Boot-3.2.0-green)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-11%2B-blue)](https://www.oracle.com/java/)
[![Redis](https://img.shields.io/badge/Redis-7.0-red)](https://redis.io)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

A production-ready rate limiting solution implementing the **Token Bucket algorithm**, designed for modern distributed systems.

## 🌟 Key Features
- 🛡️ **IP-based rate limiting** with customizable thresholds
- ⚡ **Redis-backed** atomic operations using Lua scripts
- 🔗 **AOP integration** for non-invasive implementation
- 📈 **Dynamic configuration** per endpoint
- 🧪 **Tested** with Spring Boot 3.x

## 📦 Tech Stack
- **Core**: Java 11+, Spring Boot 3
- **Data**: Redis 7.x
- **Scripting**: Lua
- **AOP**: Spring AspectJ
- **Build**: Maven

## ⚙️ Architecture
```plaintext
           +--------------+       +-----------------+
Request -->| AOP Aspect    |------>| Lua Script      |
           | (@RateLimited)|       | (Token Bucket)  |
           +--------------+       +-----------------+
                         ↓              ↓
                   Business Logic     Redis
```

📂 Project Structure
```plaintext
src/main/
├── java/
│   └── com/
│       └── example/
│           └── ratelimiter/
│               ├── annotation/   # Custom annotations
│               ├── aspect/       # AOP implementation
│               ├── config/       # Redis configuration
│               ├── service/      # Rate limiting logic
│               └── controller/   # REST endpoints
└── resources/
└── scripts/                  # Lua scripts
```
🧠 Core Components
```
Component	           Description
@RateLimited	Annotation for rate-limited methods
RateLimiterAspect	AOP aspect handling rate limit enforcement
RateLimiterService	Service layer for Redis interactions
token_bucket.lua	Lua script for atomic Redis operations
```

🌐 Roadmap
Custom rate limit keys (API keys, user sessions)
Distributed tracing integration
Prometheus metrics exporter
Dynamic configuration reload
Rate limit tiers (gold/silver/bronze users)


## Functionality
- Version 1.0.0
  - first commit
