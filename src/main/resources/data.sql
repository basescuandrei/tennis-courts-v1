insert into guest(id, name) values(null, 'Roger Federer');
insert into guest(id, name) values(null, 'Rafael Nadal');

insert into tennis_court(id, name) values(null, 'Roland Garros - Court Philippe-Chatrier');
insert into tennis_court(id, name) values(null, 'Romania - Court Philippe-Chatrier');
insert into tennis_court(id, name) values(null, 'Italy - Court Philippe-Chatrier');

insert
    into
        schedule
        (id, start_date_time, end_date_time, tennis_court_id)
    values
        (null, '2023-12-20T20:00:00.0', '2023-12-20T21:00:00.0', 1);

insert
into
    schedule
(id, start_date_time, end_date_time, tennis_court_id)
values
    (null, '2023-12-20T20:00:00.0', '2023-12-20T21:00:00.0', 1);

insert
into
    schedule
(id, start_date_time, end_date_time, tennis_court_id)
values
    (null, '2023-12-20T20:00:00.0', '2023-12-20T21:00:00.0', 2);


insert
into
    schedule
(id, start_date_time, end_date_time, tennis_court_id)
values
    (null, '2023-12-20T20:00:00.0', '2023-12-20T21:00:00.0', 3);


insert
    into
        reservation
        (id, date_create, guest_id, schedule_id, reservation_status, value)
    values
        (1, '2023-12-20T20:00:00.0', 1, 1, 0, 90);

insert into schedule_reservations (schedule_id, reservations_id) values(1, 1);