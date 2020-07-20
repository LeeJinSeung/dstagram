DROP TABLE user IF EXISTS;
DROP TABLE post IF EXISTS;
DROP TABLE image IF EXISTS;

create table user (
    uid int,
    email varchar(255) not null,
    name varchar(255),
    nickname varchar(255) not null,
    password varchar(255) not null,
    primary key (uid)
)

create table post (
    pid int,
    uid int,
    contents varchar(255),
    created timestamp,
    updated timestamp,
    primary key (pid),
    foreign key (uid)
)

create table image (
    iid int auto_increment,
    pid int,
    imageUrl varchar(255),
    primary key (iid),
    foreign key (pid)
)

