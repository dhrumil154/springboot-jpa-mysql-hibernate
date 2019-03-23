package com.kjit.Diekraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kjit.Diekraft.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, String> {

}
