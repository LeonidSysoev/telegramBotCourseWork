package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private NotificationTaskRepository repository;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            long chatId = update.message().chat().id();
            SendMessage message = new SendMessage(chatId, "Добро пожаловать!");
            Pattern pattern = Pattern.compile("[([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)]");
            Matcher matcher = pattern.matcher(update.message().text());
            if (update.message() != null && update.message().text() != null) {
                if (update.message().text().equals("/start")) {
                    SendResponse response = telegramBot.execute(message);
                } else {
                    if (matcher.matches()) {
                        LocalDateTime localDateTime = localDateTimeParse(matcher.group(1));
                        String text = matcher.group(3);
                        repository.save(new NotificationTask(update.message().chat().id(), text, localDateTime));
                    }

                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private LocalDateTime localDateTimeParse(String text) {
        try {
            return LocalDateTime.parse(text,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        } catch (Exception e) {
            logger.error("Cannot parse: {}", e);
        }
        return null;
    }


}
