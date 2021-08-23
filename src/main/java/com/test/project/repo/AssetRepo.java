package com.test.project.repo;

import com.test.project.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetRepo extends JpaRepository<Asset,String> {
}