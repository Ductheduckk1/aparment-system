package com.example.demo.service;

public interface EmailService {
    void sendBillEmail(String to, String subject, String content);
}
