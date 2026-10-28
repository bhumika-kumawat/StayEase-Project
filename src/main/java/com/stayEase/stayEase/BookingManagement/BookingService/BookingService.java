package com.stayEase.stayEase.BookingManagement.BookingService;

import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingResponseDTO;
import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import com.stayEase.stayEase.BookingManagement.BookingRepo.BookingRepo;
import com.stayEase.stayEase.BookingManagement.Enum.BookingStaus;
import com.stayEase.stayEase.CustomExceptions.DuplicateResourceFoundException;
import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import com.stayEase.stayEase.Rooms.RoomRepo.RoomRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    BookingRepo bookingRepo;
    RoomRepo roomRepo;

    BookingService(BookingRepo bookingRepo , RoomRepo roomRepo) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

//    create method for booking

    public BookingResponseDTO createBooking(
            BookingRequestDTO bookingRequestDTO,
            Long roomId) {

        // 1. Validate booking dates
        if (!bookingRequestDTO.getCheckOutDate()
                .isAfter(bookingRequestDTO.getCheckInDate())) {
            throw new IllegalArgumentException(
                    "Check-out date must be after check-in date."
            );
        }

        // 2. Find the room using the provided room ID
        RoomEntity room = roomRepo.findById(roomId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found with ID: " + roomId
                        )
                );

        // 3. Check whether the room has an overlapping booking
        boolean overlappingBooking =
                bookingRepo
                        .existsByRoomEntity_RoomIdAndCheckInDateLessThanAndCheckOutDateGreaterThan(
                                room.getRoomId(),
                                bookingRequestDTO.getCheckOutDate(),
                                bookingRequestDTO.getCheckInDate()
                        );

        // 4. Reject the request if the dates overlap
        if (overlappingBooking) {
            throw new IllegalArgumentException(
                    "Room is already booked for the selected dates."
            );
        }

        // 5. Convert the request DTO into an entity
        BookingEntity bookingEntity =
                mapToBookingEntity(bookingRequestDTO);

        // 6. Associate the booking with the selected room
        bookingEntity.setRoomEntity(room);

        // 7. Save the booking
        BookingEntity savedBooking =
                bookingRepo.save(bookingEntity);

        // 8. Convert the saved entity into a response DTO
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
        bookingEntity.setCheckInTime(bookingRequestDTO.getCheckInTime());
        bookingEntity.setCheckOutTime(bookingRequestDTO.getCheckOutTime());

        // Associate the booking with the selected room
        bookingEntity.setRoomEntity(roomEntity);

        // Save the updated booking and return its response DTO
        return mapToBookingDto(bookingRepo.save(bookingEntity));
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

    bookingEntity.setBookingStatus(BookingStaus.PENDING);
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

        bookingResponseDTO.setMessage("your booking successfully completed ");
        return bookingResponseDTO;
    }
}
