insert into roles values (1, 'TENANT');
insert into roles values (2, 'LANDLORD');

ALTER TABLE booked_rooms ADD COLUMN user_login VARCHAR(50) NOT NULL;
