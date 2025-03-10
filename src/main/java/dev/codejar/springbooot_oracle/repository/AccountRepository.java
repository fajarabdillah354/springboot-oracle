package dev.codejar.springbooot_oracle.repository;

import dev.codejar.springbooot_oracle.models.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {


}
