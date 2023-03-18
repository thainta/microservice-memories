package com.example.postservice.service.implement;

import com.example.postservice.entity.CommentsEntity;
import com.example.postservice.exeption.CommentNotFoundException;
import com.example.postservice.model.Comments;
import com.example.postservice.repository.repositoryJPA.CommentsRepository;
import com.example.postservice.repository.repositoryJPA.PostsRepository;
import com.example.postservice.repository.repositoryJPA.UsersRepository;
import com.example.postservice.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public Comments createComment(long postId,long userId,Comments comments){
        comments.setReplyTo(comments.getReplyTo() != null ? comments.getReplyTo() : 0);
        comments.setUsers(usersRepository.findById(userId).isPresent() ? usersRepository.findById(userId).get() : null);
        comments.setPost(postsRepository.findById(postId).isPresent() ? postsRepository.findById(postId).get() : null);
        kafkaTemplate.send("comments",comments.getCmtContent()+ ","+userId+","+comments.getReplyTo()+","+postId);
        if(userId != (Objects.requireNonNull(postsRepository.findById(postId).isPresent() ? postsRepository.findById(postId).get() : null)).getUser().getUser_id()){ // If the user comment in the post is their own --> not display notification
            kafkaTemplate.send("notifications","5"+","+userId+"," + postId); // Send notifications of post
        }
        return comments;
    }

    @Override
    public List<Comments> getAllCommentsPost(long postId) {
        List<CommentsEntity> commentsEntities = commentsRepository.findAllByPost(postsRepository.findById(postId).get());

        return commentsEntities.stream().map(
                cmt -> new Comments(
                        cmt.getCmtId(),
                        cmt.getCmtContent(),
                        cmt.getUsers(),
                        cmt.getReplyTo(),
                        cmt.getPost(),
                        cmt.getCreateAt(),
                        cmt.getUpdateAt(),
                        cmt.getIsArchieved()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public Comments getCommentById(Long id) {
        CommentsEntity commentsEntity = commentsRepository.findById(id).get();
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsEntity,comments);
        return comments;
    }

    @Override
    public Comments updateComment(Long id, Comments comments) throws CommentNotFoundException {
        try {
            CommentsEntity commentsEntity = commentsRepository.findById(id).get();
            commentsEntity.setUpdateAt(LocalDateTime.now());
            commentsEntity.setCmtContent(comments.getCmtContent());
            commentsEntity.setReplyTo(comments.getReplyTo());
            commentsRepository.save(commentsEntity);
            return comments;
        }catch (NoSuchElementException e){
            throw new CommentNotFoundException(String.format("Could not found any post with Id %s", id));
        }
    }

    @Override
    public boolean deleteComment(Long id) throws CommentNotFoundException {
        try {
            commentsRepository.deleteById(id);
            return true;
        }
        catch (NoSuchElementException e){
            throw new CommentNotFoundException(String.format("Could not found any post with Id %s", id));
        }
    }
}
