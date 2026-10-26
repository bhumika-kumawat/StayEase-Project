package com.stayEase.stayEase.Rooms.RoomController;

import com.stayEase.stayEase.Rooms.RoomDtos.RoomRequestDTO;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomResponseDTO;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomUpdateRequestDTO;
import com.stayEase.stayEase.Rooms.RoomDtos.RoomUpdateResponseDTO;
import com.stayEase.stayEase.Rooms.RoomService.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    RoomService roomService;

    RoomController (RoomService roomService)
    {
        this.roomService = roomService;
    }


//    create
    @PostMapping("/create")
    public ResponseEntity<RoomResponseDTO>createRoom(
            @RequestBody RoomRequestDTO roomRequestDTO , @RequestParam Long Hotel_id)
    {
        RoomResponseDTO roomResponseDTO = roomService.create(roomRequestDTO , Hotel_id);
        return ResponseEntity.ok(roomResponseDTO);
    }

//    get room by id
    @GetMapping("/getRoom/{room_id}")
    public ResponseEntity<RoomResponseDTO>getRoomById(@PathVariable Long room_id)
    {
        RoomResponseDTO getRoom = roomService.getRoomById(room_id);
        return ResponseEntity.ok(getRoom);

    }

//    get all rooms in one get request

    @GetMapping
    public ResponseEntity <List<RoomResponseDTO>>getAllRooms()
    {
        List<RoomResponseDTO> getAllRecords = roomService.getAllRooms();
        return ResponseEntity.ok(getAllRecords);
    }

//    update rooms by room_id
    @PutMapping("/update/{room_id}")
    public ResponseEntity<RoomUpdateResponseDTO>updateRoom(@RequestBody RoomUpdateRequestDTO roomUpdateRequestDTO
            , @PathVariable Long room_id )
    {
        RoomUpdateResponseDTO updatedRoomRecord = roomService.updateRoomById(roomUpdateRequestDTO , room_id);
        return ResponseEntity.ok(updatedRoomRecord);
    }

//    delete room records by room_id
    @DeleteMapping("/delete")
    public ResponseEntity <Void> deleteRoomRecord(@RequestParam Long room_id)
    {
        roomService.deleteRoomById(room_id);
        return ResponseEntity.noContent().build();
    }
}
