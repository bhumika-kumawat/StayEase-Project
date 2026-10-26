package com.stayEase.stayEase.Rooms.RoomService;

import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import com.stayEase.stayEase.Hotels.HotelRepo.HotelRepo;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomRequestDTO;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomResponseDTO;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomUpdateRequestDTO;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomUpdateResponseDTO;
import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import com.stayEase.stayEase.Rooms.RoomRepo.RoomRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    RoomRepo roomRepo;
    HotelRepo hotelRepo;

    RoomService(RoomRepo roomRepo , HotelRepo hotelRepo)
    {
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
    }

//     create rooms

    public RoomResponseDTO create(RoomRequestDTO roomRequestDTO , Long Hotel_id)
    {
        RoomEntity roomEntity = mapToRoomEntity(roomRequestDTO , Hotel_id);
        RoomEntity savedRoom = roomRepo.save(roomEntity);
        return mapToRoomDTO(savedRoom);

    }

//    get rooms by id
    public RoomResponseDTO getRoomById(Long room_id)
    {
        RoomEntity existingRoom = roomRepo.findById(room_id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "For this room_id room is not present" + room_id
                ));
        return mapToRoomDTO(existingRoom);
    }

//    get all rooms in one get request

    public List<RoomResponseDTO> getAllRooms()
    {
        List <RoomEntity> allGetRecords = roomRepo.findAll();
        return allGetRecords.stream()
                .map(this::mapToRoomDTO)
                .toList();
    }

//    update rooms

    public RoomUpdateResponseDTO updateRoomById (RoomUpdateRequestDTO roomUpdateRequestDTO,Long room_id)
    {
        RoomEntity existingRoom = roomRepo.findById(room_id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Invalid update id " + room_id
                ));

        existingRoom.setCapacity(roomUpdateRequestDTO.getCapacity());
        existingRoom.setDescription(roomUpdateRequestDTO.getDescription());
        existingRoom.setPricePerNight(roomUpdateRequestDTO.getPricePerNight());
        existingRoom.setRoomName(roomUpdateRequestDTO.getRoomName());
        existingRoom.setRoomType(roomUpdateRequestDTO.getRoomType());

        RoomEntity updatedRoom = roomRepo.save(existingRoom);

        return mapToUpdateRoomDTO(updatedRoom);
    }

//    delete room records

    public void deleteRoomById(Long id) {
        if (!roomRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Hotel not found with ID: " + id
            );
        }
        roomRepo.deleteById(id);
    }


//    Here we are mapping request dto back into a room entity

    public RoomEntity mapToRoomEntity(RoomRequestDTO roomRequestDTO , Long Hotel_id)
    {
       HotelEntity hotel = hotelRepo.findById(Hotel_id).orElseThrow(() -> new ResponseStatusException(
               HttpStatus.NOT_FOUND,
               "Hotel not found with ID: " + Hotel_id
       ));

       RoomEntity roomEntity = new RoomEntity();

       roomEntity.setCapacity(roomRequestDTO.getCapacity());
       roomEntity.setDescription(roomRequestDTO.getDescription());
       roomEntity.setPricePerNight(roomRequestDTO.getPricePerNight());
       roomEntity.setRoomName(roomRequestDTO.getRoomName());
       roomEntity.setRoomType(roomRequestDTO.getRoomType());
       roomEntity.setHotel(hotel);

       return roomEntity;
    }

//    here we are converting room entity into a room response dto

    public RoomResponseDTO mapToRoomDTO(RoomEntity roomEntity)
    {
        RoomResponseDTO roomResponseDTO =  new RoomResponseDTO();

        roomResponseDTO.setCapacity(roomEntity.getCapacity());
        roomResponseDTO.setDescription(roomEntity.getDescription());
        roomResponseDTO.setPricePerNight(roomEntity.getPricePerNight());
        roomResponseDTO.setRoomType(roomEntity.getRoomType());
        roomResponseDTO.setRoomName(roomEntity.getRoomName());

        return roomResponseDTO;
    }

//    maping room update entity into room update response dto

    public RoomUpdateResponseDTO mapToUpdateRoomDTO (RoomEntity roomEntity)
    {
        RoomUpdateResponseDTO roomUpdateResponseDTO = new RoomUpdateResponseDTO();

        roomUpdateResponseDTO.setCapacity(roomEntity.getCapacity());
        roomUpdateResponseDTO.setDescription(roomEntity.getDescription());
        roomUpdateResponseDTO.setPricePerNight(roomEntity.getPricePerNight());
        roomUpdateResponseDTO.setRoomName(roomEntity.getRoomName());
        roomUpdateResponseDTO.setRoomType(roomEntity.getRoomType());

        return roomUpdateResponseDTO;
    }

}
