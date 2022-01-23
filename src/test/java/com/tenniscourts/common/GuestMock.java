package com.tenniscourts.common;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.guests.GuestDTO;

public class GuestMock {

    public static Guest getGuestMock() {
        return Guest.builder().name("Guest Mock").build();
    }

    public static GuestDTO getGuestDtoMock() {
        return GuestDTO.builder().name("GuestDto Mock").build();
    }
}
