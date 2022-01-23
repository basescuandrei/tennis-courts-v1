package com.tenniscourts.guest;

import com.tenniscourts.guests.GuestController;
import com.tenniscourts.guests.GuestDTO;
import com.tenniscourts.guests.GuestService;
import com.tenniscourts.reservations.ReservationController;
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

import java.util.Arrays;
import java.util.List;

import static com.tenniscourts.common.GuestMock.getGuestDtoMock;
import static com.tenniscourts.common.ReservationMock.getReservationDtoMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationController.class)
public class GuestControllerTest {

    @InjectMocks
    GuestController cut;

    @Mock
    GuestService guestService;

    @Test
    public void createGuestWithValidDataSuccess() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        GuestDTO guestDtoMock = getGuestDtoMock();
        when(guestService.createUpdateGuest(guestDtoMock)).thenReturn(guestDtoMock);

        ResponseEntity<GuestDTO> result = cut.createGuest(guestDtoMock);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(guestService, times(1)).createUpdateGuest(guestDtoMock);
    }

    @Test
    public void updateGuestWithValidDataSuccess() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        GuestDTO guestDtoMock = getGuestDtoMock();
        when(guestService.createUpdateGuest(guestDtoMock)).thenReturn(guestDtoMock);

        ResponseEntity<GuestDTO> result = cut.updateGuest(guestDtoMock);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(guestService, times(1)).createUpdateGuest(guestDtoMock);
    }


    @Test
    public void deleteGuestByIdSuccess() {
        when(guestService.deleteGuest(1L)).thenReturn(1L);

        ResponseEntity<Long> result = cut.deleteGuest(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(guestService, times(1)).deleteGuest(1L);
    }

    @Test
    public void getGuestByIdSuccess() {
        when(guestService.findById(1L)).thenReturn(getGuestDtoMock());

        ResponseEntity<GuestDTO> result = cut.getGuest(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(guestService, times(1)).findById(1L);
    }


    @Test
    public void getGuestByNameSuccess() {
        String guestName = "Guest name";
        when(guestService.findByName(guestName)).thenReturn(List.of(getGuestDtoMock()));

        ResponseEntity<List<GuestDTO>> result = cut.getGuest(guestName);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(guestService, times(1)).findByName(guestName);
    }

    @Test
    public void getGuestSuccess() {
        when(guestService.findAll()).thenReturn(List.of(getGuestDtoMock()));

        ResponseEntity<List<GuestDTO>> result = cut.getGuest();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(guestService, times(1)).findAll();
    }

}
