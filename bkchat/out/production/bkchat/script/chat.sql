/*==============================================================*/
/* dbms name:      mysql 5.0                                    */
/* created on:     22/07/2017 1:38:53                           */
/*==============================================================*/


drop table if exists destination_message;

drop table if exists groups;

drop table if exists group_user;

drop table if exists message;

drop table if exists status;

drop table if exists users;

/*==============================================================*/
/* table: users                                                 */
/*==============================================================*/
create table users
(
   user_id              bigint not null AUTO_INCREMENT,
   firstname            varchar(200),
   lastname             varchar(200),
   email                varchar(150),
   username             varchar(100),
   password             varchar(200),
   status               varchar(50),
   primary key (user_id)
);

/*==============================================================*/
/* table: status                                                */
/*==============================================================*/
create table status
(
   status_id            bigint not null AUTO_INCREMENT,
   name                 varchar(300),
   primary key (status_id)
);

/*==============================================================*/
/* table: groups                                                */
/*==============================================================*/
create table groups
(
   group_id             bigint not null AUTO_INCREMENT,
   user_id              bigint,
   name                 varchar(300),
   logo                 varchar(100),
   creation_date        date,
   primary key (group_id)
);

/*==============================================================*/
/* table: message                                               */
/*==============================================================*/
create table message
(
   message_id           bigint not null AUTO_INCREMENT,
   user_id              bigint,
   status_id            bigint,
   content              varchar(500),
   creation_date        date,
   primary key (message_id)
);

/*==============================================================*/
/* table: destination_message                                   */
/*==============================================================*/
create table destination_message
(
   destination_message_id bigint not null AUTO_INCREMENT,
   user_id              bigint not null,
   message_id           bigint not null,
   primary key (destination_message_id)/*user_id, message_id, */
);

/*==============================================================*/
/* table: group_user                                            */
/*==============================================================*/
create table group_user
(
   group_user_id        bigint not null AUTO_INCREMENT,
   user_id              bigint not null,
   group_id             bigint not null,
   primary key (group_user_id) /*user_id, group_id,*/
);

alter table destination_message add constraint fk_messagedestination foreign key (message_id)
      references message (message_id) on delete restrict on update restrict;

alter table destination_message add constraint fk_userdestination foreign key (user_id)
      references users (user_id) on delete restrict on update restrict;

alter table groups add constraint fk_groupuser foreign key (user_id)
      references users (user_id) on delete restrict on update restrict;

alter table group_user add constraint fk_groupgroupuser foreign key (group_id)
      references groups (group_id) on delete restrict on update restrict;

alter table group_user add constraint fk_usergroupuser foreign key (user_id)
      references users (user_id) on delete restrict on update restrict;

alter table message add constraint fk_messagestatus foreign key (status_id)
      references status (status_id) on delete restrict on update restrict;

alter table message add constraint fk_messageuser foreign key (user_id)
      references users (user_id) on delete restrict on update restrict;

