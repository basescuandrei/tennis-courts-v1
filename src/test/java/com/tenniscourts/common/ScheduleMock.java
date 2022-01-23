package com.tenniscourts.common;

import com.tenniscourts.schedules.Schedule;

import java.time.LocalDateTime;

import static com.tenniscourts.common.TennisCourtMock.getTennisCourtMock;

public class ScheduleMock {

    public static Schedule getScheduleMock() {
        return Schedule.builder()
                .tennisCourt(getTennisCourtMock())
                .startDateTime(LocalDateTime.now().plusDays(1))
                .endDateTime(LocalDateTime.now().plusDays(2)).build();
    }

}
