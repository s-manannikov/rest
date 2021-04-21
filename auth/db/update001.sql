create table person (
    id serial primary key not null,
    login varchar(2000),
    password varchar(2000)
);

insert into person (login, password) values ('user1', '123');
insert into person (login, password) values ('user2', '123');
insert into person (login, password) values ('user3', '123');