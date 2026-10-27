package com.stayEase.stayEase.BookingManagement.BookingController;

import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingResponseDTO;
import com.stayEase.stayEase.BookingManagement.BookingService.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")

public class BookingController {

    BookingService bookingService;

    BookingController(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

     // create booking
    @PostMapping
    public ResponseEntity<BookingResponseDTO>createBooking(@RequestBody BookingRequestDTO bookingRequestDTO ,
    @RequestParam Long room_id)
    {
        BookingResponseDTO createdBooking = bookingService.createBooking(bookingRequestDTO , room_id);
        return ResponseEntity.ok(createdBooking);

    }

    // Get a booking by ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(
            @PathVariable Long bookingId) {

        // Call the service to retrieve the requested booking
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {

        // Return all bookings with HTTP 200 OK
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Update an existing booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
            @PathVariable Long bookingId,
            @RequestBody BookingRequestDTO bookingRequestDTO) {

        // Pass the booking ID and updated details to the service
        return ResponseEntity.ok(
                bookingService.updateBooking(bookingId, bookingRequestDTO));
    }

    // Delete a booking by ID
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {

        // Delete the requested booking through the service
        bookingService.deleteBooking(bookingId);

        // Return HTTP 204 No Content after successful deletion
        return ResponseEntity.noContent().build();
    }

}
