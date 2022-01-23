package com.tenniscourts.guest;

import com.tenniscourts.common.GuestMock;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.guests.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static com.tenniscourts.common.GuestMock.getGuestDtoMock;
import static com.tenniscourts.common.GuestMock.getGuestMock;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GuestService.class)
public class GuestServiceTest {

    @InjectMocks
    private GuestService cut;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private GuestMapper guestMapper;

    @Test
    public void createUpdateGuestWithValidDataSuccess() {
        GuestDTO guestDto = getGuestDtoMock();
        Guest guest = getGuestMock();
        when(guestMapper.map(guestDto)).thenReturn(guest);
        when(guestRepository.save(guest)).thenReturn(guest);
        when(guestMapper.map(guest)).thenReturn(guestDto);

        GuestDTO result = cut.createUpdateGuest(guestDto);

        assertNotNull(result);
        verify(guestMapper, times(1)).map(guestDto);
        verify(guestRepository, times(1)).save(guest);
        verify(guestMapper, times(1)).map(guest);
    }

    @Test
    public void deleteGuestWithValidGuestIdSuccess() {
        when(guestRepository.existsById(1L)).thenReturn(true);

        Long result = cut.deleteGuest(1L);

        assertEquals(1, result);
        verify(guestRepository, times(1)).existsById(1L);
        verify(guestRepository, times(1)).deleteById(1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteGuestWithInvalidGuestIdThrowException() {
        when(guestRepository.existsById(1L)).thenReturn(false);

        cut.deleteGuest(1L);

        verify(guestRepository, times(1)).existsById(1L);
        verify(guestRepository, times(0)).deleteById(1L);
    }

    @Test
    public void findByIdWithValidGuestIdSuccess() {
        Guest guestMock = getGuestMock();
        when(guestRepository.findById(1L)).thenReturn(Optional.ofNullable(guestMock));
        when(guestMapper.map(guestMock)).thenReturn(getGuestDtoMock());

        GuestDTO result = cut.findById(1L);

        assertNotNull(result);
        verify(guestRepository, times(1)).findById(1L);
        verify(guestMapper, times(1)).map(guestMock);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdWithInvalidGuestIdThrowException() {
        Guest guestMock = getGuestMock();
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        cut.findById(1L);

        verify(guestRepository, times(1)).findById(1L);
        verify(guestMapper, times(0)).map(guestMock);
    }

    @Test
    public void findByNameSuccess() {
        String mockGuestName = "Mock name";
        Guest guestMock = getGuestMock();
        when(guestRepository.findAllByName(mockGuestName)).thenReturn(List.of(guestMock));
        when(guestMapper.map(guestMock)).thenReturn(getGuestDtoMock());

        List<GuestDTO> result = cut.findByName(mockGuestName);

        assertEquals(1, result.size());
        verify(guestRepository, times(1)).findAllByName(mockGuestName);
        verify(guestMapper, times(1)).map(guestMock);
    }

    @Test
    public void findAllGuestsSuccess() {
        Guest guestMock = getGuestMock();
        when(guestRepository.findAll()).thenReturn(List.of(guestMock));
        when(guestMapper.map(guestMock)).thenReturn(getGuestDtoMock());

        List<GuestDTO> result = cut.findAll();

        assertEquals(1, result.size());
        verify(guestRepository, times(1)).findAll();
        verify(guestMapper, times(1)).map(guestMock);
    }


}
