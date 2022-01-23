package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    public GuestDTO createUpdateGuest(GuestDTO guestDto) {
        Guest guest = guestMapper.map(guestDto);
        return guestMapper.map(guestRepository.save(guest));
    }

    public Long deleteGuest(Long guestId) {
        if (guestRepository.existsById(guestId)) {
            guestRepository.deleteById(guestId);
            //TODO what should we do in case of ConstraintViolationException?
            // option1: remove also all reservation for this guest id
            // option2: throw InvalidArgumentException("This guest cannot be removed, there are reservation for this guest id)
            return guestId;
        } else {
            throw new EntityNotFoundException("Guest with id:" + guestId + " not found.");
        }
    }

    public GuestDTO findById(Long guestId) {
        return guestRepository.findById(guestId).map(guestMapper::map)
                .orElseThrow(() -> {
                            throw new EntityNotFoundException("Guest not found.");
                        }
                );
    }

    public List<GuestDTO> findByName(String guestName) {
        return guestRepository.findAllByName(guestName)
                .stream().map(guestMapper::map).collect(Collectors.toList());
    }

    public List<GuestDTO> findAll() {
        return guestRepository.findAll()
                .stream().map(guestMapper::map).collect(Collectors.toList());
    }

}