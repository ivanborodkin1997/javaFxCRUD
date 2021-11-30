create table contract
(
    id                  integer not null
        constraint contract_pk
            primary key,
    number              integer not null,
    date_of_conclusion  date    not null,
    date_of_last_update date    not null,
    check_box           boolean not null
);