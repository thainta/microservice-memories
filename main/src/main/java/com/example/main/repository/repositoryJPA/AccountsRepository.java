package com.example.main.repository.repositoryJPA;


import com.example.main.entity.AccountsEntity;
import com.example.main.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity,Long> {
    Optional<AccountsEntity> findByEmail(String email);
    List<AccountsEntity> findAllByRoles(RolesEntity roles);
    List<AccountsEntity> findTop10ByOrderByCreateAtDesc();

    Long countAllByRoles(RolesEntity roles);
}