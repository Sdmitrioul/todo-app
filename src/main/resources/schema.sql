create table if not exists Lists (
    id int primary key not null,
    name varchar(64) not null
);

create table if not exists Tasks (
    id int primary key not null,
    parentId int references Lists(id),
    done boolean,
    name varchar(64) not null,
    description varchar(256)
)