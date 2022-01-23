package com.tenniscourts.schedule;

import com.tenniscourts.common.DateUtils;
import com.tenniscourts.schedules.ScheduleController;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.schedules.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ScheduleController.class)
public class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController cut;

    @Mock
    private ScheduleService scheduleService;

    @Test
    public void findSchedulesByDates() {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(2);
        LocalDateTime startDateTime = DateUtils.localDateToLocalDateTime(startDate, 0, 0);
        LocalDateTime endDateTime = DateUtils.localDateToLocalDateTime(endDate, 23, 59);

        ResponseEntity<List<ScheduleDTO>> result = cut.findSchedulesByDates(startDate, endDate);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(scheduleService, times(1)).findSchedulesByDates(startDateTime, endDateTime);
    }

}
