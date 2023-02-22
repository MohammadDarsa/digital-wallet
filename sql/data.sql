CREATE USER pepsi WITH PASSWORD 'cola';

ALTER USER pepsi WITH SUPERUSER;

create table currency
(
    id                 bigint      not null
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    exchange_value     double precision,
    image_path         varchar(255),
    iso_name           varchar(255)
);

alter table currency
    owner to pepsi;

create table roles
(
    id   serial
        primary key,
    name varchar(20)
);

alter table roles
    owner to pepsi;

create table users
(
    id                 bigserial
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    email              varchar(50)
        constraint uk6dotkott2kjsp8vw4d0m25fb7
            unique,
    password           varchar(120),
    username           varchar(20)
        constraint ukr43af9ap4edm43mmtq01oddj6
            unique
);

alter table users
    owner to pepsi;

create table profile
(
    id                 bigint      not null
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    dob                date,
    first_name         varchar(255),
    gender             varchar(255),
    image_path         varchar(255),
    last_name          varchar(255),
    mobile_number      varchar(255),
    national_id        varchar(255),
    nationality        varchar(255),
    user_id            bigint
        constraint uk_c1dkiawnlj6uoe6fnlwd6j83j
            unique
        constraint fks14jvsf9tqrcnly0afsv0ngwv
            references users
);

alter table profile
    owner to pepsi;

create table user_roles
(
    user_id bigint  not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id integer not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    primary key (user_id, role_id)
);

alter table user_roles
    owner to pepsi;

create table wallet
(
    id                 bigint      not null
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    reference_id       uuid,
    user_id            bigint
        constraint uk_hgee4p1hiwadqinr0avxlq4eb
            unique
        constraint fkgbusavqq0bdaodex4ee6v0811
            references users
);

alter table wallet
    owner to pepsi;

create table balance
(
    id                 bigint      not null
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    amount             double precision,
    currency_id        bigint
        constraint fk45r0l2sc0cdja19no0orl9i95
            references currency,
    wallet_id          bigint
        constraint fkeljc5cyrwcmqecjhwmsy2r5wb
            references wallet
);

alter table balance
    owner to pepsi;

create table card
(
    id                 bigint      not null
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    display_name       varchar(255),
    expired            boolean,
    expiry             date,
    masked_pan         varchar(255),
    token              varchar(255),
    wallet_id          bigint
        constraint fkimankogjrf9gnaj13v65xabqy
            references wallet
);

alter table card
    owner to pepsi;

create table transaction
(
    id                 bigint      not null
        primary key,
    created_by         varchar(50),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(50),
    last_modified_date timestamp(6) with time zone,
    amount             double precision,
    description        varchar(255),
    reference_id       varchar(255),
    type               varchar(255),
    wallet_id          bigint
        constraint fktfwlfspv2h4wcgc9rjd1658a6
            references wallet
);

alter table transaction
    owner to pepsi;

INSERT INTO public.roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO public.roles (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO public.roles (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO public.users (id, email, password, username) VALUES (4, 'admin@mail.com', '$2a$10$iU7QlanbsNyqLYCOwoYDH.rUTJGLooXBVnuycByUS/6ggq769atKS', 'admin');
INSERT INTO public.users (id, email, password, username) VALUES (7, 'admin2@mail.com', '$2a$10$2v/WvftodzFkEQwaOY.SYe0OJzN6gQyX0i3sYq3iZ0sPHqzaD6sEW', 'admin2');

INSERT INTO public.currency (id, created_by, created_date, last_modified_by, last_modified_date, exchange_value, image_path, iso_name) VALUES (252, null, '2023-02-19 10:59:12.807653 +00:00', null, '2023-02-19 10:59:12.807653 +00:00', 1, '', 'USD');
INSERT INTO public.currency (id, created_by, created_date, last_modified_by, last_modified_date, exchange_value, image_path, iso_name) VALUES (253, null, '2023-02-19 10:59:29.982281 +00:00', null, '2023-02-19 10:59:29.982281 +00:00', 80000, '', 'LBP');

INSERT INTO public.user_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (7, 3);

INSERT INTO public.wallet (id, created_by, created_date, last_modified_by, last_modified_date, reference_id, user_id) VALUES (202, null, '2023-02-19 11:47:20.823006 +00:00', null, '2023-02-19 11:47:20.823006 +00:00', 'fb85f093-dc4c-4812-88e9-33b77ee9e0fc', 4);
INSERT INTO public.wallet (id, created_by, created_date, last_modified_by, last_modified_date, reference_id, user_id) VALUES (352, null, '2023-02-19 16:22:28.887155 +00:00', null, '2023-02-19 16:22:28.887155 +00:00', '43758877-503c-471e-be04-7611476ec11e', 7);

INSERT INTO public.balance (id, created_by, created_date, last_modified_by, last_modified_date, amount, currency_id, wallet_id) VALUES (52, null, '2023-02-19 12:52:52.777824 +00:00', null, '2023-02-19 12:52:52.777824 +00:00', 0, 253, 202);
INSERT INTO public.balance (id, created_by, created_date, last_modified_by, last_modified_date, amount, currency_id, wallet_id) VALUES (2, null, '2023-02-19 11:47:20.838522 +00:00', null, '2023-02-19 11:47:20.838522 +00:00', 550, 252, 202);
INSERT INTO public.balance (id, created_by, created_date, last_modified_by, last_modified_date, amount, currency_id, wallet_id) VALUES (102, null, '2023-02-19 16:22:28.908467 +00:00', null, '2023-02-19 16:22:28.908467 +00:00', 150, 252, 352);

INSERT INTO public.profile (id, created_by, created_date, last_modified_by, last_modified_date, dob, first_name, gender, image_path, last_name, mobile_number, national_id, nationality, user_id) VALUES (2, null, '2023-02-19 11:47:20.870885 +00:00', null, '2023-02-19 11:47:20.870885 +00:00', null, null, null, null, null, null, null, null, 4);
INSERT INTO public.profile (id, created_by, created_date, last_modified_by, last_modified_date, dob, first_name, gender, image_path, last_name, mobile_number, national_id, nationality, user_id) VALUES (52, null, '2023-02-19 16:22:28.936863 +00:00', null, '2023-02-19 16:22:28.936863 +00:00', null, null, null, null, null, null, null, null, 7);

INSERT INTO public.transaction (id, created_by, created_date, last_modified_by, last_modified_date, amount, description, reference_id, type, wallet_id) VALUES (2, null, '2023-02-19 15:21:59.206962 +00:00', null, '2023-02-19 15:21:59.206962 +00:00', 100, 'Top up from card with pan 1122334455667788 of currency USD.', '71febb2e-69a2-4def-aa3f-8b79928f60a4', 'TOPUP', 202);
INSERT INTO public.transaction (id, created_by, created_date, last_modified_by, last_modified_date, amount, description, reference_id, type, wallet_id) VALUES (52, null, '2023-02-19 16:23:10.436090 +00:00', null, '2023-02-19 16:23:10.436090 +00:00', 100, 'Top up from card with pan 1122334455667788 of currency USD.', 'de57a186-6cd3-4339-8c82-ac9625570c7b', 'TOPUP', 352);
INSERT INTO public.transaction (id, created_by, created_date, last_modified_by, last_modified_date, amount, description, reference_id, type, wallet_id) VALUES (53, null, '2023-02-19 16:24:38.683497 +00:00', null, '2023-02-19 16:24:38.683497 +00:00', 50, 'Transfer in of currency USD from wallet fb85f093-dc4c-4812-88e9-33b77ee9e0fc.', 'e52b9714-6dd8-4047-abfc-4795a92c5014', 'TRANSFER_IN', 352);
INSERT INTO public.transaction (id, created_by, created_date, last_modified_by, last_modified_date, amount, description, reference_id, type, wallet_id) VALUES (54, null, '2023-02-19 16:24:38.689944 +00:00', null, '2023-02-19 16:24:38.689944 +00:00', 50, 'Transfer out of currency USD to wallet 43758877-503c-471e-be04-7611476ec11e.', '30a6298a-69dd-4b26-9d79-1e20f057c11c', 'TRANSFER_OUT', 202);


CREATE database bank;