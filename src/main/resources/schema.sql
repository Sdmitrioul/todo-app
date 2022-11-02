create table if not exists Lists (
    id integer primary key autoincrement not null,
    name varchar(64) not null
);

create table if not exists Tasks (
    id integer primary key autoincrement not null,
    parentId integer references Lists(id),
    done boolean,
    name varchar(64) not null,
    description varchar(256)
)