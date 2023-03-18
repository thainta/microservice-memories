package com.example.friendservice.service.interfaces;

import com.example.friendservice.exeption.FriendRequestsNotFoundException;
import com.example.friendservice.model.FriendRequests;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequests> getAllFriendRequests();
    FriendRequests getFriendRequestById(long id);
    List<FriendRequests> getFriendRequestsBySendUserId(long userId);
    List<FriendRequests> getFriendRequestsByReceiveUserId(long userId);
    FriendRequests createFriendRequest(long userId, FriendRequests request) throws Exception;
    FriendRequests updateFriendRequest(long id, FriendRequests request) throws FriendRequestsNotFoundException;
    FriendRequests deleteFriendRequest(long id) throws FriendRequestsNotFoundException;
    boolean acceptFriendRequest(long id) throws FriendRequestsNotFoundException;
    boolean cancelFriendRequest(long id) throws FriendRequestsNotFoundException;
}
