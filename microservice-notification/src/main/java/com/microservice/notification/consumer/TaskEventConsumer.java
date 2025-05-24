package com.microservice.notification.consumer;

import com.microservice.notification.config.RabbitMQConfig;
import com.microservice.notification.dtos.ResponseTaskBlockDTO;
import com.microservice.notification.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskEventConsumer {

    private EmailService emailService;



    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleTasksCompleted(ResponseTaskBlockDTO completedTasks) {
        String emailDestinatario = completedTasks.getUserEmail();
        String emailAsunto = "Se completó las tareas de: " + completedTasks.getTaskBlockTitle();
        StringBuilder emailMenssage = new StringBuilder();

        String blockTitle = "<b>" + completedTasks.getTaskBlockTitle() + ":</b></br>";
        emailMenssage.append(blockTitle);

        System.out.println("¡Mensaje recibido desde RabbitMQ!");
        for (String task : completedTasks.getTaskTitles()) {
            emailMenssage.append(task).append("<br>");
        }

        emailService.sendEmail(
                emailDestinatario,
                emailAsunto,
                emailMenssage.toString());
    }
}