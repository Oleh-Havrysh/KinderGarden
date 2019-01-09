CREATE TABLE marks(
   id varchar(64) primary key,
   date  date,
   comment varchar(100),
   behaviour int,
   sleeping int,
   eating int,
   child_id varchar(64) references children(id)
);

create table children(
id varchar(64) primary key,
name varchar(100),
surname varchar(100),
birth_date date,
group_id varchar(64) references groups(id),
parent_id varchar(64) references humans(id),
notice varchar(300)
);

create table announcements(
id varchar(64) primary key,
title varchar(30),
content varchar(500),
creation_date timestamp,
expires timestamp
);


create table humans(
id varchar(64) primary key,
name varchar(100),
surname varchar(100),
address varchar(300),
phone varchar(15),
email varchar(100),
role int,
login varchar(50),
password varchar(100)
);

create table messages(
id varchar(64) primary key,
content varchar(500),
fro varchar(64) references humans(id),
too varchar(64) references humans(id)
);

create table groups(
id varchar(64) primary key,
name varchar(20),
teacher_id varchar(64) references humans(id)
);