package com.example.postservice.repository.repositoryJPA;

import com.example.postservice.entity.CommentsEntity;
import com.example.postservice.entity.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity,Long> {
    List<CommentsEntity> findAllByPost(PostsEntity post);
}

