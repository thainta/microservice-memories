package com.example.searchservice.repository.repositoryJPA;
import com.example.searchservice.entity.SearchRecentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRecentsRepository extends JpaRepository<SearchRecentsEntity, Long> {
}
