package com.studyverse.controllers;

import com.studyverse.dto.NotificationDTO;
import com.studyverse.exceptions.NotificationNotFoundException;
import com.studyverse.models.Notification;
import com.studyverse.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint pentru obținerea tuturor notificărilor
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.findAll();
    }

    // Endpoint pentru crearea unei notificări
    @PostMapping
    public Notification createNotification(@RequestBody @Valid NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setUserId(notificationDTO.getUserId());
        notification.setCreatedAt(notificationDTO.getCreatedAt());

        return notificationService.save(notification);
    }

    // Endpoint pentru obținerea unei notificări după ID
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationService.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification not found with id " + id));
    }

    // Endpoint pentru actualizarea unei notificări
    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody @Valid NotificationDTO notificationDTO) {
        Notification notification = notificationService.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification not found with id " + id));
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setUserId(notificationDTO.getUserId());
        notification.setCreatedAt(notificationDTO.getCreatedAt());

        return notificationService.save(notification);
    }

    // Endpoint pentru ștergerea unei notificări
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteById(id);
    }
}
