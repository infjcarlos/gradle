/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21/07/2017 10:45:45                          */
/*==============================================================*/


--drop table if exists DESTINATION_MESSAGE;

drop table if exists GROUPS;

drop table if exists GROUP_USER;

--drop table if exists MESSAGE;

--drop table if exists STATUS;

drop table if exists USERS;

/*==============================================================*/
/* Table: DESTINATION_MESSAGE                                   */
/*==============================================================*/
/*
create table DESTINATION_MESSAGE
(
   USER_ID              bigint not null,
   MESSAGE_ID           bigint not null,
   DESTINATION_MESSAGE_ID bigint not null,
   primary key (USER_ID, MESSAGE_ID, DESTINATION_MESSAGE_ID)
);
*/
/*==============================================================*/
/* Table: GROUP                                                 */
/*==============================================================*/
create table GROUP
(
   GROUP_ID             bigint not null,
   USER_ID              bigint not null,
   NAME                 varchar(200),
   LOGO                 varchar(100),
   CREATION_DATE        date,
   primary key (USER_ID, GROUP_ID)
);

/*==============================================================*/
/* Table: GROUP_USER                                            */
/*==============================================================*/
create table GROUP_USER
(
   USER_ID              bigint not null,
   GROUP_ID             bigint not null,
   GROUP_USER_ID        bigint not null,
   primary key (USER_ID, GROUP_ID, GROUP_USER_ID)
);

/*==============================================================*/
/* Table: MESSAGE                                               */
/*==============================================================*/
/*
create table MESSAGE
(
   MESSAGE_ID           bigint not null,
   USER_ID              bigint,
   STATUS_ID            bigint,
   CONTENT              varchar(500),
   CREATION_DATE        date,
   primary key (MESSAGE_ID)
);
*/
/*==============================================================*/
/* Table: STATUS                                                */
/*==============================================================*/
/*
create table STATUS
(
   STATUS_ID            bigint not null,
   NAME                 varchar(300),
   primary key (STATUS_ID)
);
*/

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   USER_ID              bigint not null,
   FIRSTNAME            varchar(200),
   LASTNAME             varchar(200),
   EMAIL                varchar(150),
   USERNAME             varchar(100),
   PASSWORD             varchar(200),
   STATUS               varchar(50),
   primary key (USER_ID)
);
/*
alter table DESTINATION_MESSAGE add constraint FK_MESSAGEDESTINATION foreign key (MESSAGE_ID)
      references MESSAGE (MESSAGE_ID) on delete restrict on update restrict;

alter table DESTINATION_MESSAGE add constraint FK_USERDESTINATION foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;
*/
alter table GROUP add constraint FK_GROUPUSER foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;

alter table GROUP_USER add constraint FK_GROUPGROUPUSER foreign key (GROUP_ID)
      references GROUP (GROUP_ID) on delete restrict on update restrict;

alter table GROUP_USER add constraint FK_USERGROUPUSER foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;
/*
alter table MESSAGE add constraint FK_MESSAGESTATUS foreign key (STATUS_ID)
      references STATUS (STATUS_ID) on delete restrict on update restrict;

alter table MESSAGE add constraint FK_MESSAGEUSER foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;

*/