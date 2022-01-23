package com.tenniscourts.schedule;

import com.tenniscourts.common.ScheduleMock;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.schedules.ScheduleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ScheduleMapperTest {

    private ScheduleMapper cut = Mappers.getMapper( ScheduleMapper.class);

    @Test
    public void mapScheduleMapperToScheduleDto() {
        Schedule schedule = ScheduleMock.getScheduleMock();

        ScheduleDTO result = cut.map(schedule);

        assertEquals(schedule.getId(), result.getId());
        assertEquals(schedule.getTennisCourt().getId(), result.getTennisCourt().getId());
        assertEquals(schedule.getTennisCourt().getName(), result.getTennisCourt().getName());
        assertEquals(schedule.getTennisCourt().getId(), result.getTennisCourtId());
        assertEquals(schedule.getStartDateTime(), result.getStartDateTime());
        assertEquals(schedule.getEndDateTime(), result.getEndDateTime());
    }

}
