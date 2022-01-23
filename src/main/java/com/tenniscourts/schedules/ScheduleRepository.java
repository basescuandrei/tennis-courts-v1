package com.tenniscourts.schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByTennisCourt_IdOrderByStartDateTime(Long id);

    @Query(value = "select sch from Schedule as sch left join Reservation as res on sch.id = res.schedule.id where sch.startDateTime >= :startDate" +
            " and sch.endDateTime <= :endDate and (res.schedule is NULL or res.reservationStatus is not 0)")
    List<Schedule> findAllFreeSchedules(LocalDateTime startDate, LocalDateTime endDate);

}