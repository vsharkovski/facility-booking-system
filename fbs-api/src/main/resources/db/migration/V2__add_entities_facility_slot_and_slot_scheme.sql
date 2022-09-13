create table facilities
(
    id                    bigserial primary key not null,
    creation_time         timestamp             not null,
    update_time           timestamp             not null,
    creator_id            bigserial references users (id),
    name                  varchar(50)           not null,
    location              varchar(50)           not null,
    registration_note     varchar(10000),
    default_slot_capacity integer
);

create table slot_schemes
(
    id            bigserial primary key not null,
    creation_time timestamp             not null,
    update_time   timestamp             not null,
    creator_id    bigserial references users (id),
    name          varchar(50)           not null
);

create table slots
(
    id             bigserial primary key not null,
    creation_time  timestamp             not null,
    update_time    timestamp             not null,
    creator_id     bigserial references users (id),
    type           varchar(20)           not null,
    slot_scheme_id bigserial references slot_schemes (id),
    facility_id    bigserial references facilities (id),
    start_time     timestamp,
    end_time       timestamp,
    capacity       integer
);

create table slot_users
(
    id      bigserial primary key,
    slot_id bigserial references slots (id) on delete cascade not null,
    user_id bigserial references users (id) on delete cascade not null
)