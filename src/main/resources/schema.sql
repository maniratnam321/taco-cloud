create schema if not exists taco;

grant all privileges on schema taco to taco_cloud_user;

set search_path to taco;

drop table taco_ingredients;

drop table ingredient;

drop table taco_order_tacos;

drop table taco;

drop table taco_order;

create table if not exists ingredient
(
    id   varchar(4)  not null primary key,
    name varchar(25) not null,
    type varchar(15) not null
);

create table if not exists taco
(
    id SERIAL primary key,
    name varchar(50) not null ,
    createdAt timestamp not null
);

create table if not exists taco_ingredients
(
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table taco_ingredients add primary key(taco, ingredient);
alter table taco_ingredients add foreign key(taco) references taco(id);
alter table taco_ingredients add foreign key(ingredient) references ingredient(id);

create table if not exists taco_order
(
    id serial not null primary key ,
    name varchar(20) not null,
    address varchar(100) not null,
    city varchar(40) not null,
    state varchar(20) not null,
    zip varchar(5) not null,
    creditCardNumber varchar(20) not null,
    creditCardExpiry varchar(10) not null,
    credCardCvv varchar(30) not null,
    placedAt timestamp not null
);

create table if not exists taco_order_tacos
(
    taco bigint not null,
    taco_order bigint not null
);

alter table taco_order_tacos add primary key(taco, taco_order);
alter table taco_order_tacos add foreign key(taco) references taco(id);
alter table taco_order_tacos add foreign key(taco_order) references taco_order(id);