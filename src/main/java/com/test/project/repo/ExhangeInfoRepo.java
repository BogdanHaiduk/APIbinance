package com.test.project.repo;

import com.test.project.entity.ExhangeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExhangeInfoRepo extends JpaRepository<ExhangeInfo, String> {
    List<ExhangeInfo> findAll();
}
