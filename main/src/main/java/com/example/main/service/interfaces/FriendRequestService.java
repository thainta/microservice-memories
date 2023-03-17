package com.example.main.service.interfaces;

import com.example.main.exeption.FriendRequestsNotFoundException;
import com.example.main.model.FriendRequests;

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
