package com.tenniscourts.schedule;

import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = {"com.tenniscourts.schedules"})
@EntityScan(basePackages = {"com.tenniscourts"})
@Sql(scripts = "insert.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "clean.sql", executionPhase = AFTER_TEST_METHOD)
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository cut;

    @Test
    public void findAllFreeSchedulesByValidDatesWithOneResult() {
        LocalDateTime startLocalDateTime = LocalDateTime.parse("3023-12-20T20:00:00.0");
        LocalDateTime endLocalDateTime = LocalDateTime.parse("3023-12-20T21:00:00.0");

        List<Schedule> result = cut.findAllFreeSchedules(startLocalDateTime, endLocalDateTime);

        assertEquals(1, result.size());
        assertEquals(11, result.get(0).getId());
    }

    @Test
    public void findAllFreeSchedulesByValidDatesWithoutResults() {
        LocalDateTime startLocalDateTime = LocalDateTime.parse("3023-12-20T22:00:00.0");
        LocalDateTime endLocalDateTime = LocalDateTime.parse("3023-12-20T23:00:00.0");

        List<Schedule> result = cut.findAllFreeSchedules(startLocalDateTime, endLocalDateTime);

        assertEquals(0, result.size());
    }

}
