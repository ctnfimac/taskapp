package com.microservice.notification.services;

public interface EmailService {
    void sendEmail(String destinatario, String asunto, String mensaje);
}
