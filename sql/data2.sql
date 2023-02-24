-- auto-generated definition
create table card
(
    id         bigserial
        primary key,
    amount     double precision not null,
    created_at timestamp(6),
    currency   varchar(3)       not null,
    cvv        varchar(3)       not null,
    ex_date    date             not null,
    pan        varchar(16)      not null
        constraint uk_g6uje0o3pnudu8ucgxnci3x2p
            unique,
    updated_at timestamp(6)
);

alter table card
    owner to pepsi;


INSERT INTO card(amount, created_at, currency, cvv, ex_date, pan, updated_at) VALUES (1000000,'2020-01-22','USD', '123', '2024-01-01', '1111222233334444' ,'2020-01-22');
INSERT INTO card( amount, created_at, currency, cvv, ex_date, pan, updated_at) VALUES (2000000,'2020-01-22','LBP', '123', '2024-01-01', '1234123412341234' ,'2020-01-22');
INSERT INTO card( amount, created_at, currency, cvv, ex_date, pan, updated_at) VALUES (2000000,'2020-01-22','USD', '123', '2024-01-01', '1122334455667788' ,'2020-01-22');
INSERT INTO card( amount, created_at, currency, cvv, ex_date, pan, updated_at) VALUES (3000000,'2020-01-22','LBP', '123', '2024-01-01', '1212121212121212' ,'2020-01-22');