package com.test.project.repo;

import com.test.project.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepo extends JpaRepository<Filter,String> {
}
