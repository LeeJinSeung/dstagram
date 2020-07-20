DROP TABLE user IF EXISTS;
DROP TABLE post IF EXISTS;

create table user (
    uid varchar(255).
    email varchar(255) not null,
    name varchar(255),
    nickname varchar(255) not null,
    password varchar(255) not null,
    primary key (uid)
)

create table post (
    pid int,
    uid varchar(255).
    contents varchar(255),
    created timestamp,
    updated timestamp,
    primary key (pid),
    foreign key (uid)
)

create table image (
    iid int,
    pid int,
    imageUrl varchar(255),
    primary key (iid),
    foreign key (pid)
)


