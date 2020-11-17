CREATE TABLE if not exists rooms_reservations
(
    hotel_id       INTEGER,
    room_id        INTEGER,
    arrival_date   TIMESTAMP NOT NULL,
    departure_date TIMESTAMP NOT NULL,
    CONSTRAINT hotels_hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id) ON DELETE CASCADE,
    CONSTRAINT rooms_room_id_fk FOREIGN KEY (room_id) REFERENCES rooms (room_id) ON DELETE CASCADE
);