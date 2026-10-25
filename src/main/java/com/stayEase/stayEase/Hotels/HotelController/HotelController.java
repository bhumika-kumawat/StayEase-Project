package com.stayEase.stayEase.Hotels.HotelController;

import com.stayEase.stayEase.Hotels.HotelDtos.HotelRequestDTO;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelResponseDto;
import com.stayEase.stayEase.Hotels.HotelService.HotelService;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelUpdateReqDto;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelUpdateResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Hotel")
public class HotelController {

    HotelService hotelService;

    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

//    create hotel

    @PostMapping("/createHotel")
    public ResponseEntity<HotelResponseDto> create(@RequestBody HotelRequestDTO hotelRequestDTO) {
        HotelResponseDto createdResponse = hotelService.createHotel(hotelRequestDTO);
        return ResponseEntity.ok(createdResponse);

    }

//    get hotel by id

    @GetMapping("/getHotel/{hotel_id}")
    public ResponseEntity<HotelResponseDto> getHotelById(@PathVariable Long hotel_id) {
        HotelResponseDto getRecord = hotelService.getHotel(hotel_id);
        return ResponseEntity.ok(getRecord);

    }

//    get all hotel records

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelResponseDto>> getHotelById() {
        List<HotelResponseDto> getRecord = hotelService.getAllHotel();
        return ResponseEntity.ok(getRecord);

    }

    // update Hotel

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelUpdateResponseDto> updateHotel(@RequestBody HotelUpdateReqDto hotelUpdateReqDto, @PathVariable Long id) {
        HotelUpdateResponseDto updatedRecord = hotelService.update(hotelUpdateReqDto, id);
        return ResponseEntity.ok(updatedRecord);
    }


// delete record

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        boolean deleted = hotelService.deleteHotel(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Hotel deleted successfully");
    }
}
