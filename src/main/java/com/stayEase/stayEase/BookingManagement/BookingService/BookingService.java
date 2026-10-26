package com.stayEase.stayEase.BookingManagement.BookingService;

import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingRequestDTO;
import com.stayEase.stayEase.BookingManagement.BookingDTOS.BookingResponseDTO;
import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import com.stayEase.stayEase.BookingManagement.BookingRepo.BookingRepo;
import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import com.stayEase.stayEase.Rooms.RoomRepo.RoomRepo;
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

    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO , Long room_id) {
        BookingEntity bookingEntity = mapToBookingEntity(bookingRequestDTO , room_id);
        bookingRepo.save(bookingEntity);
        return mapToBookingDto(bookingEntity);

    }


    // Get a booking by its ID
    public BookingResponseDTO getBookingById(Long bookingId) {

        // Find the booking in the database or throw an exception if not found
        BookingEntity bookingEntity = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

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

        // Find the existing booking or throw an exception if it does not exist
        BookingEntity bookingEntity = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Find the room selected for the updated booking
        RoomEntity roomEntity = roomRepo.findById(bookingRequestDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

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
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Delete the booking from the database
        bookingRepo.delete(bookingEntity);
    }

    //method to convert dto into entity
    
    public BookingEntity mapToBookingEntity(
        BookingRequestDTO bookingRequestDTO, Long room_id) {

    BookingEntity bookingEntity = new BookingEntity();

    bookingEntity.setBookingStatus(bookingRequestDTO.getBookingStatus());
    bookingEntity.setCheckInTime(bookingRequestDTO.getCheckInTime());
    bookingEntity.setCheckOutTime(bookingRequestDTO.getCheckOutTime());

    RoomEntity roomEntity = roomRepo.findById(room_id)
            .orElseThrow(() -> new RuntimeException("Room not found"));

    bookingEntity.setRoomEntity(roomEntity);

    return bookingEntity;
}
//    method to convert entity into a DTO

    public BookingResponseDTO mapToBookingDto (BookingEntity bookingEntity)
    {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        bookingResponseDTO.setBookingStatus(bookingEntity.getBookingStatus());
        bookingResponseDTO.setCheckInTime(bookingEntity.getCheckInTime());
        bookingResponseDTO.setCheckOutTime(bookingEntity.getCheckOutTime());
        bookingResponseDTO.setMessage("your booking successfully completed ");

        return bookingResponseDTO;
    }
}
