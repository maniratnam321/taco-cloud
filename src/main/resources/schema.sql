create schema if not exists taco;

grant all privileges on schema taco to taco_cloud_user;

set search_path to taco;

create table if not exists ingredient
(
    id   varchar(4)  not null,
    name varchar(25) not null,
    type varchar(15) not null
);
