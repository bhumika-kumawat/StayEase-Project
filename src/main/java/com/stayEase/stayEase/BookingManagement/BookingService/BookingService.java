package com.stayEase.stayEase.BookingManagement.BookingService;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingResponseDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingStatusRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import com.stayEase.stayEase.BookingManagement.BookingRepo.BookingRepo;
import com.stayEase.stayEase.BookingManagement.Enum.BookingStatus;
import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import com.stayEase.stayEase.Rooms.RoomRepo.RoomRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
public class BookingService {

    BookingRepo bookingRepo;
    RoomRepo roomRepo;

    BookingService(BookingRepo bookingRepo , RoomRepo roomRepo) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

//    create method for booking

    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {

        // 1. Check whether the requested room exists.
        RoomEntity existingRoom = roomRepo.findById(bookingRequestDTO.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room ID is invalid"));

        // 2. Combine dates and times to validate the complete booking period.
        LocalDateTime requestedStart = LocalDateTime.of(
                bookingRequestDTO.getCheckInDate(),
                bookingRequestDTO.getCheckInTime()
        );

        LocalDateTime requestedEnd = LocalDateTime.of(
                bookingRequestDTO.getCheckOutDate(),
                bookingRequestDTO.getCheckOutTime()
        );

        // 3. Checkout must be after check-in.
        if (!requestedEnd.isAfter(requestedStart)) {
            throw new IllegalArgumentException(
                    "Checkout date and time must be after check-in date and time."
            );
        }

        // 4. Check whether the requested dates overlap an existing booking.
        boolean dateOverlap = bookingRepo.existsDateOverlap(
                bookingRequestDTO.getRoomId(),
                bookingRequestDTO.getCheckInDate(),
                bookingRequestDTO.getCheckOutDate()
        );

        if (dateOverlap) {
            throw new IllegalArgumentException(
                    "The room is already booked for the requested dates."
                            + "Please choose different dates."
            );
        }

        // 5. If the dates are available, check whether the requested
        // time conflicts with an existing booking on those dates.
        boolean timeOverlap = bookingRepo.existsTimeOverlap(
                bookingRequestDTO.getRoomId(),
                bookingRequestDTO.getCheckInDate(),
                bookingRequestDTO.getCheckInDate(),
                bookingRequestDTO.getCheckInTime(),
                bookingRequestDTO.getCheckOutTime()
        );

        if (timeOverlap) {
            throw new IllegalArgumentException(
                    "The room is unavailable for the requested time. "
                            + "Please choose a different check-in or check-out time."
            );
        }

//        calculating total price for booking

        BigDecimal priceForRoom = existingRoom.getPricePerNight();
        long totalNights = DAYS.between(bookingRequestDTO.getCheckInDate() ,bookingRequestDTO.getCheckOutDate());
        BigDecimal totalPrice = priceForRoom.multiply(BigDecimal.valueOf(totalNights));


        // 6. Map the request to an entity and associate the selected room.
        BookingEntity bookingEntity = mapToBookingEntity(bookingRequestDTO);
        bookingEntity.setBookingStatus(BookingStatus.PENDING);
        bookingEntity.setRoomEntity(existingRoom);
        bookingEntity.setTotalPrice(totalPrice);

        // 7. Save only after all validations pass.
        BookingEntity savedBooking = bookingRepo.save(bookingEntity);

        // 8. Return the saved booking as a response DTO.
        return mapToBookingDto(savedBooking);
    }

    // Get a booking by its ID
    public BookingResponseDTO getBookingById(Long bookingId) {

        // Find the booking in the database or throw an exception if not found
        BookingEntity bookingEntity = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Convert the booking entity into a response DTO
        return mapToBookingDto(bookingEntity);
    }

    // Get all bookings from the database
    public List<BookingResponseDTO> getAllBookings() {

        // Retrieve all booking records
        return bookingRepo.findAll()
                .stream()

                // Convert each booking entity into a response DTO
                .map(this::mapToBookingDto)
                .toList();
    }

    // Update an existing booking using its ID
    public BookingResponseDTO updateBooking(
            Long bookingId, BookingRequestDTO bookingRequestDTO) {

        // Find the room selected for the updated booking
        RoomEntity roomEntity = roomRepo.findById(bookingRequestDTO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        // Find the existing booking or throw an exception if it does not exist
        BookingEntity bookingEntity = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Update the booking's check-in and check-out times

        LocalDateTime checkIn = LocalDateTime.of(
                bookingRequestDTO.getCheckInDate(),
                bookingRequestDTO.getCheckInTime()
        );

        LocalDateTime checkOut = LocalDateTime.of(
                bookingRequestDTO.getCheckOutDate(),
                bookingRequestDTO.getCheckOutTime()
        );

        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException(
                    "Checkout must be after check-in"
            );
        }

    //     logic to update the total price in entity

        BigDecimal roomPrice = roomEntity.getPricePerNight();
        long updatedNights = ChronoUnit.DAYS.between(bookingRequestDTO.getCheckInDate() , bookingRequestDTO.getCheckOutDate());
        BigDecimal calculateUpdatedNights = roomPrice.multiply(BigDecimal.valueOf(updatedNights));
        .
        bookingEntity.setCheckInDate(bookingRequestDTO.getCheckInDate());
        bookingEntity.setCheckInTime(bookingRequestDTO.getCheckInTime());

        bookingEntity.setCheckOutDate(bookingRequestDTO.getCheckOutDate());
        bookingEntity.setCheckOutTime(bookingRequestDTO.getCheckOutTime());

         // Associate the booking with the selected room
       bookingEntity.setRoomEntity(roomEntity);
       bookingEntity.setTotalPrice(calculateUpdatedNights);

        // Save the updated booking and return its response DTO
        return mapToBookingDto(bookingRepo.save(bookingEntity));
    }

    //   Update Booking Status :  patch update method , update booking status
    public BookingResponseDTO updateBookingStatus(
            Long bookingId,
            BookingStatusRequestDTO bookingStatusRequestDTO) {

        BookingEntity existingBooking = bookingRepo.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid booking Id")
                );

        BookingStatus currentStatus = existingBooking.getBookingStatus();
        BookingStatus newStatus = bookingStatusRequestDTO.getBookingStatus();

        if (currentStatus == BookingStatus.CANCELLED) {

            throw new IllegalArgumentException(
                    "Cancelled Booking cannot be updated."
            );
        }

        else if (currentStatus == BookingStatus.PENDING) {

            if (newStatus != BookingStatus.CONFIRMED &&
                    newStatus != BookingStatus.CANCELLED) {

                throw new IllegalArgumentException(
                        "Pending bookings can only be confirmed or cancelled."
                );
            }
        }

        else if (currentStatus == BookingStatus.CONFIRMED) {

            if (newStatus != BookingStatus.CANCELLED &&
                    newStatus != BookingStatus.COMPLETED) {

                throw new IllegalArgumentException(
                        "Confirmed bookings can only be cancelled or completed."
                );
            }
        }

        else if (currentStatus == BookingStatus.COMPLETED) {

            throw new IllegalArgumentException(
                    "Completed bookings cannot be updated."
            );
        }

        // Updating booking status after successful validation
        existingBooking.setBookingStatus(newStatus);

        // Save updated booking
        bookingRepo.save(existingBooking);

        // Return updated booking response
        return mapToBookingDto(existingBooking);
    }


    // Delete an existing booking using its ID
    public void deleteBooking(Long bookingId) {

        // Find the booking or throw an exception if it does not exist
        BookingEntity bookingEntity = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Delete the booking from the database
        bookingRepo.delete(bookingEntity);
    }

    //method to convert dto into entity

    public BookingEntity mapToBookingEntity(
        BookingRequestDTO bookingRequestDTO) {

    BookingEntity bookingEntity = new BookingEntity();

    bookingEntity.setBookingStatus(BookingStatus.PENDING);
    bookingEntity.setCheckInDate(bookingRequestDTO.getCheckInDate());
    bookingEntity.setCheckOutDate(bookingRequestDTO.getCheckOutDate());
    bookingEntity.setCheckInTime(bookingRequestDTO.getCheckInTime());
    bookingEntity.setCheckOutTime(bookingRequestDTO.getCheckOutTime());


    return bookingEntity;
}
//    method to convert entity into a DTO

    public BookingResponseDTO mapToBookingDto (BookingEntity bookingEntity)
    {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        bookingResponseDTO.setBookingStatus(bookingEntity.getBookingStatus());
        bookingResponseDTO.setCheckInTime(bookingEntity.getCheckInTime());
        bookingResponseDTO.setCheckOutTime(bookingEntity.getCheckOutTime());
        bookingResponseDTO.setCheckInDate(bookingEntity.getCheckInDate());
        bookingResponseDTO.setCheckOutDate(bookingEntity.getCheckOutDate());
        bookingResponseDTO.setBookingId(bookingEntity.getBookingId());
        bookingResponseDTO.setRoomId(bookingEntity.getRoomEntity().getRoomId());
        bookingResponseDTO.setTotalPrice(bookingEntity.getTotalPrice());

        bookingResponseDTO.setMessage("your booking successfully completed ");
        return bookingResponseDTO;
    }
}
