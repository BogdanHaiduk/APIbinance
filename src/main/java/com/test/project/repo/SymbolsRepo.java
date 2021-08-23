package com.test.project.repo;

import com.test.project.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolsRepo extends JpaRepository<Symbol,String> {

}
