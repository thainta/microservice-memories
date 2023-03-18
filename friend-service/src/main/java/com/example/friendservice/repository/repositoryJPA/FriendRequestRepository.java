package com.example.friendservice.repository.repositoryJPA;
import com.example.friendservice.entity.FriendRequestEntity;
import com.example.friendservice.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Long> {
    Optional<List<FriendRequestEntity>> findAllBySendUser(UsersEntity user);
    Optional<List<FriendRequestEntity>> findAllByReceiveUser(UsersEntity user);

}
