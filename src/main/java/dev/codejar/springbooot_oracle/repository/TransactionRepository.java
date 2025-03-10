package dev.codejar.springbooot_oracle.repository;

import dev.codejar.springbooot_oracle.models.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {


}


