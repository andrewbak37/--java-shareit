package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInfoDto;
import ru.practicum.shareit.booking.model.BookingState;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingInfoDto bookItem(@RequestBody BookingDto bookingDto,
                                   @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.bookItem(bookingDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingInfoDto approveBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                         @PathVariable Long bookingId,
                                         @RequestParam("approved") Boolean approved) {
        return bookingService.approveBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingInfoDto getBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                     @PathVariable Long bookingId) {
        return bookingService.getBooking(userId, bookingId);
    }

    @GetMapping()
    public List<BookingInfoDto> getBookings(@RequestHeader("X-Sharer-User-Id") Long userId,
                                            @RequestParam(value = "state") BookingState state,
                                            @RequestParam(value = "from") int from,
                                            @RequestParam(value = "size") int size) {
        return bookingService.getBookings(userId, state, from, size);

    }

    @GetMapping("/owner")
    public List<BookingInfoDto> getOwnerBookings(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                 @RequestParam(value = "state") BookingState state,
                                                 @RequestParam(value = "from") int from,
                                                 @RequestParam(value = "size") int size) {
        return bookingService.getOwnerBookings(userId, state, from, size);
    }
}