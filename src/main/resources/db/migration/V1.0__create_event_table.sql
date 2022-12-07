create table employer(
    id serial,
    name varchar(50) not null,
    age date not null,
    cargo varchar(75) not null,
    constraint pk_employer primary key(id),
    constraint un_id unique(id)

);