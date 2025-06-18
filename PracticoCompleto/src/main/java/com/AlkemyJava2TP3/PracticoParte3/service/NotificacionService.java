package com.AlkemyJava2TP3.PracticoParte3.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    @Async
    public void sendSmsNotification(String phoneNumber, String message) {
        // Simulate sending SMS notification
        try {
            Thread.sleep(5000); // Simulate delay
            System.out.println("SMS sent to " + phoneNumber + ": " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error sending SMS: " + e.getMessage());
        }
    }
}
