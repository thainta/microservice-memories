package com.example.main.repository.repositoryJPA;

import com.example.main.builder.AccountBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountBuilderRepository extends JpaRepository<AccountBuilder,Long> {
    Optional<AccountBuilder> findByEmail(String email);
}
