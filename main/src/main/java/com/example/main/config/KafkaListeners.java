//package com.example.main.config;
//
//
//import com.example.main.entity.CommentsEntity;
//import com.example.main.entity.NotificationsEntity;
//import com.example.main.model.Comments;
//import com.example.main.model.Notifications;
//import com.example.main.repository.repositoryJPA.CommentsRepository;
//import com.example.main.repository.repositoryJPA.NotificationsRepository;
//import com.example.main.repository.repositoryJPA.PostsRepository;
//import com.example.main.repository.repositoryJPA.UsersRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//
//@Component
//public class KafkaListeners {
//    private final RedisTemplate<String, String> redisTemplate;
//    private final CommentsRepository commentsRepository;
//    private final NotificationsRepository notificationsRepository;
//    private final UsersRepository usersRepository;
//    private final PostsRepository postsRepository;
//
//    public KafkaListeners(RedisTemplate<String,String> redisTemplate, CommentsRepository commentsRepository, NotificationsRepository notificationsRepository,PostsRepository postsRepository, UsersRepository usersRepository){
//        this.redisTemplate = redisTemplate;
//        this.commentsRepository =commentsRepository;
//        this.notificationsRepository = notificationsRepository;
//        this.postsRepository = postsRepository;
//        this.usersRepository = usersRepository;
//    }
//    @KafkaListener(
//            topics = "notifications",
//            groupId = "notifications_groupId"
//    )
//    public void notificationsListener(String data){
//        System.out.println("Listener Notification Receive: " + data);
//        String[] listKeysNotification = new String[]{"notiType","post","user"};
//        String[] listData = data.split(",");
//        HashMap<String, String> map = new HashMap<>();
//        for(int i = 0;i<listData.length;i++){
//            map.put(listKeysNotification[i],listData[i]);
//        }
//        map.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
//        NotificationsEntity notificationsEntity = new NotificationsEntity();
//        Notifications notifications = new Notifications(
//                Long.valueOf(map.get("notiType")),
//                postsRepository.findById(Long.valueOf(map.get("post"))).isPresent() ? postsRepository.findById(Long.valueOf(map.get("post"))).get() : null,
//                usersRepository.findById(Long.valueOf(map.get("user"))).isPresent() ? usersRepository.findById(Long.valueOf(map.get("user"))).get() : null
//        );
//        BeanUtils.copyProperties(notifications,notificationsEntity);
//        notificationsRepository.save(notificationsEntity);
//    }
//    @KafkaListener(
//            topics = "messages",
//            groupId = "messages_groupId"
//    )
//    public void messagesListener(String data){
//        redisTemplate.convertAndSend("messages", data);
//        System.out.println("Listener Message Receive: " + data);
//    }
//    @KafkaListener(
//            topics = "comments",
//            groupId = "comments_groupId"
//    )
//    public void commentsListener(String data){
//        String[] listKeysComments = new String[]{"cmtContent","userId","replyTo","postId"};
//        String[] listData = data.split(",");
//        HashMap<String, String> map = new HashMap<>();
//        for(int i = 0;i<listData.length;i++){
//            map.put(listKeysComments[i],listData[i]);
//        }
//        map.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
//        CommentsEntity commentsEntity = new CommentsEntity();
//        Comments comments = new Comments(
//                map.get("cmtContent"),
//                usersRepository.findById(Long.valueOf(map.get("userId"))).isPresent() ? usersRepository.findById(Long.valueOf(map.get("userId"))).get() : null,
//                Long.valueOf(map.get("replyTo")),
//                postsRepository.findById(Long.valueOf(map.get("postId"))).isPresent() ? postsRepository.findById(Long.valueOf(map.get("postId"))).get() : null
//        );
//        BeanUtils.copyProperties(comments,commentsEntity);
//        commentsRepository.save(commentsEntity);
//    }
//
//}
