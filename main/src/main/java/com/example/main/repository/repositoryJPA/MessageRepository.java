package com.example.main.repository.repositoryJPA;

import com.example.main.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByOrderByCreateAtDesc();
}
