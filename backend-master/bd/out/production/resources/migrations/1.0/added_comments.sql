CREATE TABLE if not exists comments
(
    id         serial primary key,
    hotel_id   INTEGER,
    created_by VARCHAR(30),
    title      VARCHAR(30),
    text       VARCHAR(200),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    rating     VARCHAR(10) NOT NULL,
    CONSTRAINT users_username_fk FOREIGN KEY (created_by) REFERENCES users (username) ON DELETE CASCADE,
    CONSTRAINT hotels_hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id) ON DELETE CASCADE
);
