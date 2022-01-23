package com.tenniscourts.reservations;

import com.tenniscourts.common.ReservationMock;
import com.tenniscourts.schedules.Schedule;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.tenniscourts.common.ReservationMock.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationService.class)
public class ReservationServiceTest {

    @InjectMocks
    ReservationService cut;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;


    @Test
    public void getRefundValueFullRefund() {
        Schedule schedule = new Schedule();

        LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);

        schedule.setStartDateTime(startDateTime);

        Assert.assertEquals(cut.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build()), new BigDecimal(10));
    }

    @Test
    public void bookReservationWithValidDataSuccess() {
        CreateReservationRequestDTO createReservationRequestDTO = ReservationMock.getCreateReservationRequestDTO();
        Reservation reservationMock = getReservationMock();
        when(reservationMapper.map(createReservationRequestDTO)).thenReturn(reservationMock);
        when(reservationRepository.save(reservationMock)).thenReturn(reservationMock);
        when(reservationMapper.map(reservationMock)).thenReturn(getReservationDtoMock());

        ReservationDTO result = cut.bookReservation(createReservationRequestDTO);

        assertNotNull(result);
        verify(reservationMapper, times(1)).map(createReservationRequestDTO);
        verify(reservationRepository, times(1)).save(reservationMock);
        verify(reservationMapper, times(1)).map(reservationMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rescheduleReservationWithTheSameSlotThrowException() {
        Reservation reservationMock = getReservationMock();
        when(reservationRepository.findById(1L)).thenReturn(Optional.ofNullable(reservationMock));
        when(reservationMapper.map(reservationMock)).thenReturn(getReservationDtoMock());

        cut.rescheduleReservation(1L, 1L);
    }

    @Test
    public void rescheduleReservationWithValidDataSuccess() {
        Reservation reservationMock = getReservationMock();
        ReservationDTO reservationDTOMock = getReservationDtoMock();
        when(reservationRepository.findById(1L)).thenReturn(Optional.ofNullable(reservationMock));
        when(reservationMapper.map(any(CreateReservationRequestDTO.class))).thenReturn(reservationMock);
        when(reservationMapper.map(reservationMock)).thenReturn(reservationDTOMock);
        when(reservationRepository.save(reservationMock)).thenReturn(reservationMock);

        ReservationDTO result = cut.rescheduleReservation(1L, 2L);

        assertNotNull(result);
        verify(reservationRepository, times(2)).findById(1L);
        verify(reservationRepository, times(3)).save(reservationMock);
        verify(reservationMapper, times(1)).map(any(CreateReservationRequestDTO.class));
        verify(reservationMapper,times(3)).map(reservationMock);

    }

}