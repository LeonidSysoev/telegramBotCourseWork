package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification_task")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long chatId;
    private String notificationTask;
    private LocalDateTime localDateTime;

    public NotificationTask(long chatId, String notificationTask, LocalDateTime localDateTime) {
        this.chatId = chatId;
        this.notificationTask = notificationTask;
        this.localDateTime = localDateTime;
    }

    public NotificationTask() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getNotificationTask() {
        return notificationTask;
    }

    public void setNotificationTask(String notificationTask) {
        this.notificationTask = notificationTask;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chatId == that.chatId && Objects.equals(notificationTask, that.notificationTask) && Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notificationTask, localDateTime);
    }

    @Override
    public String toString() {
        return "notificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", notificationTask='" + notificationTask + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
