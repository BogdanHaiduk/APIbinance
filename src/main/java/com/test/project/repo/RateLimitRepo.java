package com.test.project.repo;

import com.test.project.entity.RateLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateLimitRepo extends JpaRepository<RateLimit,String> {
}
