package com.example.postservice.repository.repositoryJPA;

import com.example.postservice.entity.PhotoInPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoInPostRepository extends JpaRepository<PhotoInPostEntity, Long> {
}
