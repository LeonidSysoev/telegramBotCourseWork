package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;

import java.util.List;
@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
        List<NotificationTask> findAllByLocalDateTime(LocalDateTime localDateTime);


}
