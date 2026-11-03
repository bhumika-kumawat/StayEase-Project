package com.stayEase.stayEase.BookingManagement.BookingController;

import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingPageResponseDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingResponseDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingStatusRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingService.BookingService;
import com.stayEase.stayEase.BookingManagement.BookingStatusEnum.BookingStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public ResponseEntity<BookingResponseDTO>createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO)
    {
        BookingResponseDTO createdBooking = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.ok(createdBooking);

    }

    // Get a booking by ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(
            @PathVariable Long bookingId) {

        // Call the service to retrieve the requested booking
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

//    get a booking by room id
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List <BookingResponseDTO> >getBookingByRoomId(@Valid @PathVariable Long roomId)
    {
        return ResponseEntity.ok(bookingService.getBookingsByRoomId(roomId));
    }

//    get a booking by STATUS
    @GetMapping("/status")
    public ResponseEntity<List<BookingResponseDTO>>getBookingByStatus(@Valid @RequestParam BookingStatus bookingStatus)
    {

        List<BookingResponseDTO> getAllBookingsByStatus =bookingService.getBookingByStatus(bookingStatus);
        return ResponseEntity.ok(getAllBookingsByStatus);
    }

    //    get Bookings by check In Date

    @GetMapping("/checkIn")
    public ResponseEntity<List<BookingResponseDTO>>getBookingByCheckIn(@Valid @RequestParam LocalDate checkInDate)
    {
        List<BookingResponseDTO> getAllBookingsByCheckIn =bookingService.getAllBookingByCheckIn(checkInDate);
        return ResponseEntity.ok(getAllBookingsByCheckIn);
    }

    //    get Bookings by check Out date

    @GetMapping("/checkOut")
    public ResponseEntity<List<BookingResponseDTO>>getBookingByCheckOut(@Valid @RequestParam LocalDate checkOutDate)
    {
        List<BookingResponseDTO> getAllBookingsByCheckOut =bookingService.getAllBookingByCheckOut(checkOutDate);
        return ResponseEntity.ok(getAllBookingsByCheckOut);
    }

   //    get bookings by price range

    @GetMapping("/price")
    public ResponseEntity<List<BookingResponseDTO>>getBookingByPriceRange(@RequestParam BigDecimal minPrice , @RequestParam BigDecimal  maxPrice)
    {
        List<BookingResponseDTO> getBookingsByPriceRange = bookingService.getAllBookingByPriceRange(minPrice , maxPrice);
        return ResponseEntity.ok(getBookingsByPriceRange);
    }


    // Get all bookings
    @GetMapping
    public ResponseEntity<BookingPageResponseDTO >getAllBookings(
            @RequestParam int page , int size) {

        // Return all bookings with HTTP 200 OK
        return ResponseEntity.ok(bookingService.getAllBookings(page , size));
    }

    // Update an existing booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
            @PathVariable Long bookingId,
            @Valid @RequestBody BookingRequestDTO bookingRequestDTO) {

        // Pass the booking ID and updated details to the service
        return ResponseEntity.ok(
                bookingService.updateBooking(bookingId, bookingRequestDTO));
    }

    // Delete a booking by ID
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@Valid  @PathVariable Long bookingId) {

        // Delete the requested booking through the service
        bookingService.deleteBooking(bookingId);

        // Return HTTP 204 No Content after successful deletion
        return ResponseEntity.noContent().build();
    }

//    patch method
    @PatchMapping("/{bookingId}/status")
    public ResponseEntity<BookingResponseDTO>updateBookingStatus(@PathVariable Long bookingId ,@Valid @RequestBody BookingStatusRequestDTO bookingStatusRequestDTO)
    {
       BookingResponseDTO bookingStatusUpdated = bookingService.updateBookingStatus(bookingId , bookingStatusRequestDTO);
       return ResponseEntity.ok(bookingStatusUpdated);

    }

}
