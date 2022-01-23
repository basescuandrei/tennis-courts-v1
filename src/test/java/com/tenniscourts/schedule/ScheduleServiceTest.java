package com.tenniscourts.schedule;

import com.tenniscourts.common.ScheduleMock;
import com.tenniscourts.schedules.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ScheduleService.class)
public class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService cut;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @Test(expected = IllegalArgumentException.class)
    public void findSchedulesByDatesWithStartDateFromThePast() {
        cut.findSchedulesByDates(LocalDateTime.now().minusDays(1), LocalDateTime.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findSchedulesByDatesWithEndDateSmallerThanStartDate() {
        cut.findSchedulesByDates(LocalDateTime.now().plusMonths(1), LocalDateTime.now().plusDays(1));
    }

    @Test
    public void findSchedulesByDatesWithValidDates() {
        Schedule schedule = ScheduleMock.getScheduleMock();
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endDateTime = LocalDateTime.now().plusDays(2);
        when(scheduleRepository.findAllFreeSchedules(any(), any())).thenReturn(List.of(schedule));

        List<ScheduleDTO> result = cut.findSchedulesByDates(startDateTime, endDateTime);

        assertEquals(1, result.size());
        verify(scheduleMapper, times(1)).map(schedule);
        verify(scheduleRepository, times(1)).findAllFreeSchedules(startDateTime, endDateTime);

    }

}
