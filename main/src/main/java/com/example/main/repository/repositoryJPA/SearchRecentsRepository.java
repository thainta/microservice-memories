package com.example.main.repository.repositoryJPA;
import com.example.main.entity.SearchRecentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRecentsRepository extends JpaRepository<SearchRecentsEntity, Long> {
}
