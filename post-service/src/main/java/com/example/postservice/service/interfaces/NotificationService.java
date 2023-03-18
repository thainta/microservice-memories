package com.example.postservice.service.interfaces;

import com.example.postservice.exeption.NotificationNotFoundException;
import com.example.postservice.model.Notifications;

import java.util.List;

public interface NotificationService {
    public List<Notifications> getAllNotification();
    Notifications createNotification(long userId, Notifications notification) throws Exception;
    Notifications updateNotification(long id, Notifications notification) throws NotificationNotFoundException;
    Notifications getNotificationById(long id) throws NotificationNotFoundException;
    Boolean deleteNotificationById(long id) throws NotificationNotFoundException;
    public List<Notifications> getAllNotiByUserId(long userId);

}
