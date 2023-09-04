package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class NotificationByTime {
    private TelegramBot telegramBot;
    NotificationTaskRepository repository;

    public NotificationByTime(TelegramBot telegramBot, NotificationTaskRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        repository.findAllByDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(task -> {
                    telegramBot.execute(new SendMessage(task.getChatId(), task.getNotificationTask()));
                });
    }


}
