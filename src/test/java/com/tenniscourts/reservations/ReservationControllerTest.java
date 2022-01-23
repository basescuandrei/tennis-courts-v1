package com.tenniscourts.reservations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.tenniscourts.common.ReservationMock.getCreateReservationRequestDTO;
import static com.tenniscourts.common.ReservationMock.getReservationDtoMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationController.class)
public class ReservationControllerTest {

    @InjectMocks
    ReservationController cut;

    @Mock
    private ReservationService reservationService;

    @Test
    public void bookReservationWithSuccess() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        CreateReservationRequestDTO createReservationRequestDTO = getCreateReservationRequestDTO();
        when(reservationService.bookReservation(createReservationRequestDTO)).thenReturn(getReservationDtoMock());

        ResponseEntity<Void> result =  cut.bookReservation(createReservationRequestDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(reservationService, times(1)).bookReservation(createReservationRequestDTO);
    }

    @Test
    public void cancelReservationWithSuccess() {
        ResponseEntity<ReservationDTO> result = cut.cancelReservation(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(reservationService, times(1)).cancelReservation(1L);
    }

    @Test
    public void rescheduleReservationWithSuccess() {
        ResponseEntity<ReservationDTO> result = cut.rescheduleReservation(1L, 1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(reservationService, times(1)).rescheduleReservation(1L, 1L);
    }

}
