create table if not exists hotel_images
(
    hotel_id INTEGER,
    image    VARCHAR(255),
    CONSTRAINT hotels_hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id) ON DELETE CASCADE

);
