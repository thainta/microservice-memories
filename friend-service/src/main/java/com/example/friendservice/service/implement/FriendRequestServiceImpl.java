package com.example.friendservice.service.implement;

import com.example.friendservice.entity.FriendRequestEntity;
import com.example.friendservice.entity.UsersEntity;
import com.example.friendservice.exeption.FriendRequestsNotFoundException;
import com.example.friendservice.model.FriendRequests;
import com.example.friendservice.repository.repositoryJPA.FriendRequestRepository;
import com.example.friendservice.repository.repositoryJPA.UsersRepository;
import com.example.friendservice.service.interfaces.FriendRequestService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    FriendRequestRepository friendRequestRepository;
    @Autowired
    UsersRepository usersRepository;
    @Override
    public List<FriendRequests> getAllFriendRequests() {
        List<FriendRequestEntity> friendRequestEntities = friendRequestRepository.findAll();

        return friendRequestEntities.stream().map(
                data -> new FriendRequests(
                        data.getReqId(),
                        data.getSendUser(),
                        data.getReceiveUser(),
                        data.getIsAccepted(),
                        data.getIsArchived(),
                        data.getCreateAt(),
                        data.getUpdateAt()
                        )
        ).collect(Collectors.toList());
    }

    @Override
    public FriendRequests getFriendRequestById(long id) {
        Optional<FriendRequestEntity> friendRequestEntity = friendRequestRepository.findById(id);
        if(friendRequestEntity.isPresent()) {
            FriendRequests friendRequests = new FriendRequests();
            BeanUtils.copyProperties(friendRequestEntity.get(), friendRequests);
            return friendRequests;
        }
        return null;
    }

    @Override
    public List<FriendRequests> getFriendRequestsBySendUserId(long userId) {
        UsersEntity user = usersRepository.findById(userId).isPresent() ? usersRepository.findById(userId).get() : null;
        List<FriendRequestEntity> friendRequestEntities = friendRequestRepository.findAllBySendUser(user).isPresent() ? friendRequestRepository.findAllBySendUser(user).get() : null;
        assert friendRequestEntities != null;
        return friendRequestEntities.stream().map(
                data -> new FriendRequests(
                        data.getReqId(),
                        data.getSendUser(),
                        data.getReceiveUser(),
                        data.getIsAccepted(),
                        data.getIsArchived(),
                        data.getCreateAt(),
                        data.getUpdateAt()
                )
        ).collect(Collectors.toList());
    }
    @Override
    public List<FriendRequests> getFriendRequestsByReceiveUserId(long userId) {
        UsersEntity user = usersRepository.findById(userId).isPresent() ? usersRepository.findById(userId).get() : null;
        List<FriendRequestEntity> friendRequestEntities = friendRequestRepository.findAllByReceiveUser(user).isPresent() ? friendRequestRepository.findAllByReceiveUser(user).get() : null;

        assert friendRequestEntities != null;
        return friendRequestEntities.stream().map(
                data -> new FriendRequests(
                        data.getReqId(),
                        data.getSendUser(),
                        data.getReceiveUser(),
                        data.getIsAccepted(),
                        data.getIsArchived(),
                        data.getCreateAt(),
                        data.getUpdateAt()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public FriendRequests createFriendRequest(long userId, FriendRequests request) throws Exception {
        try {
            FriendRequestEntity friendRequestEntity = new FriendRequestEntity();
            request.setCreateAt(LocalDateTime.now());
            request.setUpdateAt(LocalDateTime.now());
            request.setReceiveUser(usersRepository.findById(userId).isPresent() ? usersRepository.findById(userId).get() : null);
            BeanUtils.copyProperties(request, friendRequestEntity);
            friendRequestRepository.save(friendRequestEntity);
            return request;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public FriendRequests updateFriendRequest(long id, FriendRequests request) throws FriendRequestsNotFoundException {
        try {
            FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
            friendRequestEntity.setUpdateAt(LocalDateTime.now());
            friendRequestEntity.setIsAccepted(request.getIsAccepted());
            friendRequestRepository.save(friendRequestEntity);

            FriendRequests updateFriendRequestsResponse = new FriendRequests();
            BeanUtils.copyProperties(friendRequestEntity, updateFriendRequestsResponse);
            return updateFriendRequestsResponse;
        }
        catch (NoSuchElementException e){
            throw new FriendRequestsNotFoundException(String.format("Could not found any friend requests with Id %s", id));
        }
    }

    @Override
    public FriendRequests deleteFriendRequest(long id) throws FriendRequestsNotFoundException {
        try {
            FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
            FriendRequests friendRequests = new FriendRequests();
            BeanUtils.copyProperties(friendRequestEntity, friendRequests);
            friendRequestRepository.deleteById(id);
            return friendRequests;
        }
        catch (NoSuchElementException e){
            throw new FriendRequestsNotFoundException(String.format("Could not found any friend requests with Id %s", id));
        }
    }

    @Override
    public boolean acceptFriendRequest(long id) throws FriendRequestsNotFoundException {
        try {
            FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
            friendRequestEntity.setIsAccepted(1);
            friendRequestEntity.setIsArchived(1);
            friendRequestRepository.save(friendRequestEntity);
            return true;
        }
        catch (NoSuchElementException e){
            throw new FriendRequestsNotFoundException(String.format("Could not found any friend requests with Id %s", id));
        }
    }

    @Override
    public boolean cancelFriendRequest(long id) throws FriendRequestsNotFoundException {
        try {
            FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
            friendRequestEntity.setIsAccepted(0);
            friendRequestRepository.save(friendRequestEntity);
            return true;
        }
        catch (NoSuchElementException e){
            throw new FriendRequestsNotFoundException(String.format("Could not found any friend requests with Id %s", id));
        }
    }
}
