package com.tenniscourts.common;

import com.tenniscourts.reservations.CreateReservationRequestDTO;
import com.tenniscourts.reservations.Reservation;
import com.tenniscourts.reservations.ReservationDTO;
import com.tenniscourts.reservations.ReservationStatus;

import java.math.BigDecimal;

import static com.tenniscourts.common.GuestMock.getGuestMock;
import static com.tenniscourts.common.ScheduleMock.getScheduleMock;

public class ReservationMock {

    public static CreateReservationRequestDTO getCreateReservationRequestDTO() {
        return CreateReservationRequestDTO.builder().guestId(1L).scheduleId(1L).build();
    }

    public static ReservationDTO getReservationDtoMock() {
        return ReservationDTO.builder().id(1L).scheduledId(1L).guestId(1L).build();
    }

    public static Reservation getReservationMock() {
        return Reservation.builder()
                .guest(getGuestMock())
                .schedule(getScheduleMock())
                .value(BigDecimal.TEN)
                .reservationStatus(ReservationStatus.READY_TO_PLAY).build();
    }

}
