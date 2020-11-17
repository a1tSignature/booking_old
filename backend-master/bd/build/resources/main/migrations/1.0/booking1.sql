CREATE TABLE if not exists roles
(
    role_id INTEGER PRIMARY KEY,
    name    VARCHAR(30) NOT NULL
);

CREATE TABLE if not exists users
(
    user_id    SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    phone      VARCHAR(15)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(144) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    is_active  BOOLEAN      NOT NULL DEFAULT FALSE,
    is_locked  BOOLEAN      NOT NULL DEFAULT FALSE,
    country    VARCHAR(50),
    city       VARCHAR(50),
    street     VARCHAR(50),
    house      VARCHAR(5)
);

CREATE TABLE if not exists user_roles
(
    user_id INTEGER,
    role_id INTEGER,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT user_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE
);
CREATE TABLE if not exists hotels
(
    hotel_id    SERIAL PRIMARY KEY,
    name        VARCHAR(50),
    country     VARCHAR(50),
    city        VARCHAR(50),
    street      VARCHAR(50),
    house       VARCHAR(5),
    description VARCHAR(500),
    amenities   VARCHAR(50)[] DEFAULT '{}',
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);
CREATE TABLE if not exists rooms
(
    room_id     SERIAL PRIMARY KEY,
    hotel_id    INTEGER,
    beds        INTEGER,
    daily_Price INTEGER,
    description VARCHAR(500) DEFAULT '',
    CONSTRAINT hotels_hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id) ON DELETE CASCADE
);
CREATE TABLE if not exists booked_rooms
(
    room_id   INTEGER,
    arrival   TIMESTAMP,
    departure TIMESTAMP,
    CONSTRAINT rooms_room_id FOREIGN KEY (room_id) REFERENCES rooms (room_id) ON DELETE CASCADE
);
CREATE TABLE if not exists hotels_created_by
(
    hotel_id   INTEGER,
    user_id   INTEGER,
    CONSTRAINT hotels_hotel_id FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id) ON DELETE CASCADE,
    CONSTRAINT users_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE

);
/*
INSERT  INTO roles (role_id, name)
VALUES ('1', 'TENANT'),
       ('2', 'LANDLORD')*/



