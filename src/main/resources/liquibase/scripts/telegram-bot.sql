–- liquibase formatted sql

-- changeset lSysoev:1
create table notification_task(
    id              serial primary key,
    chatId          integer not null,
    "notification"  text not null,
    localdatetime     timestamp not null
)

