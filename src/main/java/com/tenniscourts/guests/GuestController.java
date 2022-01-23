package com.tenniscourts.guests;


import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/guest")
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDto) {
        return ResponseEntity.created(locationByEntity(guestService.createUpdateGuest(guestDto).getId())).build();
    }

    @PutMapping
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guestDto) {
        return ResponseEntity.created(locationByEntity(guestService.createUpdateGuest(guestDto).getId())).build();
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteGuest(@RequestParam("guestId") Long guestId) {
        return ResponseEntity.ok(guestService.deleteGuest(guestId));
    }

    @GetMapping(path = "/id/{guestId}")
    public ResponseEntity<GuestDTO> getGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.findById(guestId));
    }

    @GetMapping(path = "/name/{guestName}")
    public ResponseEntity<List<GuestDTO>> getGuest(@PathVariable String guestName) {
        return ResponseEntity.ok(guestService.findByName(guestName));
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getGuest() {
        return ResponseEntity.ok(guestService.findAll());
    }

}
