insert into guest(id, name)
values (10, 'Roger Federer');
insert into guest(id, name)
values (20, 'Rafael Nadal');

insert into tennis_court(id, name)
values (10, 'Roland Garros - Court Philippe-Chatrier');

insert
into schedule
    (id, start_date_time, end_date_time, tennis_court_id)
values (10, '3023-12-20T20:00:00.0', '3023-12-20T21:00:00.0', 10);

insert
into schedule
(id, start_date_time, end_date_time, tennis_court_id)
values (11, '3023-12-20T20:00:00.0', '3023-12-20T21:00:00.0', 10);

insert
into schedule
(id, start_date_time, end_date_time, tennis_court_id)
values (20, '3024-12-20T20:00:00.0', '3024-12-20T21:00:00.0', 10);

insert
into reservation
    (id, date_create, guest_id, schedule_id, reservation_status, value)
values (10, '2022-01-20T20:00:00.0', 10, 10, 0, 90);


insert into schedule_reservations (schedule_id, reservations_id)
values (10, 10);