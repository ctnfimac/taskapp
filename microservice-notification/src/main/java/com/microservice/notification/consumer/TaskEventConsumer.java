package com.microservice.notification.consumer;

import com.microservice.notification.config.RabbitMQConfig;
import com.microservice.notification.dtos.ResponseTaskBlockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleTasksCompleted(ResponseTaskBlockDTO completedTasks) {
        System.out.println("📩 ¡Mensaje recibido desde RabbitMQ!");
        for (String task : completedTasks.getTaskTitles()) {
            System.out.printf("✅ Tarea completada: %s%n", task);
        }

        // Acá podrías invocar un EmailService para enviar el correo
    }
}