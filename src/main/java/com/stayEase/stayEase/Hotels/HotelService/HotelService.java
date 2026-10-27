package com.stayEase.stayEase.Hotels.HotelService;

import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.GlobalExceptionHandler.GlobalExceptionHandler;
import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import com.stayEase.stayEase.Hotels.HotelRepo.HotelRepo;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelRequestDTO;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelResponseDto;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelUpdateReqDto;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelUpdateResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    HotelRepo hotelRepo;

    HotelService(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

//    create
    public HotelResponseDto createHotel(HotelRequestDTO hotelRequestDTO) {

        HotelEntity createdHotel = mapToEntity(hotelRequestDTO);
        HotelEntity HotelResponse = hotelRepo.save(createdHotel);

        return mapToDto(HotelResponse);
    }

//    get hotel by Id
    public HotelResponseDto getHotel(Long id) {
        HotelEntity hotel = hotelRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel with id " + id + " not found"
                        )
                );

        return mapToDto(hotel);
    }

    //    get all hotel records

    public List<HotelResponseDto> getAllHotel()
    {
        List<HotelEntity> existHotel = hotelRepo.findAll();

        List<HotelResponseDto> responseList = new ArrayList<>();

        for (HotelEntity hotel : existHotel)
        {
            responseList.add(mapToDto(hotel));
        }

        return responseList;
    }

//    update

    public HotelUpdateResponseDto update(HotelUpdateReqDto updateReqHotel , Long id)
    {

        HotelEntity existingHotel = hotelRepo.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Invalid id , can't update")
                        );

        existingHotel.setHotelAddress(updateReqHotel.getHotelAddress());
        existingHotel.setHotelDescription(updateReqHotel.getHotelDescription());
        existingHotel.setHotelPhone(updateReqHotel.getHotelPhone());
        existingHotel.setHotelPrice(updateReqHotel.getHotelPrice());

        HotelEntity savedHotel =  hotelRepo.save(existingHotel);
        return mapToUpdateDto(savedHotel);
    }

//    delete

    public void deleteHotel(Long id) {

        HotelEntity hotel = hotelRepo.findById(id).
                orElseThrow(
                        () -> new ResourceNotFoundException("invalid id , can't delete")
                );
        hotelRepo.delete(hotel);
    }


    private HotelEntity mapToEntity(HotelRequestDTO hotelRequestDTO) {


        HotelEntity Hotel = new HotelEntity();

        Hotel.setHotelAddress(hotelRequestDTO.getHotelAddress());
        Hotel.setHotelCity(hotelRequestDTO.getHotelCity());
        Hotel.setHotelDescription(hotelRequestDTO.getHotelDescription());
        Hotel.setHotelEmail(hotelRequestDTO.getHotelEmail());
        Hotel.setHotelName(hotelRequestDTO.getHotelName());
        Hotel.setHotelPhone(hotelRequestDTO.getHotelPhone());
        Hotel.setHotelPrice(hotelRequestDTO.getHotelPrice());

        return Hotel;
    }


    private HotelResponseDto mapToDto(HotelEntity hotelEntity)
    {
        HotelResponseDto hotelResponseDto = new HotelResponseDto();

        hotelResponseDto.setHotelAddress(hotelEntity.getHotelAddress());
        hotelResponseDto.setHotelDescription(hotelEntity.getHotelDescription());
        hotelResponseDto.setHotelName(hotelEntity.getHotelName());
        hotelResponseDto.setHotelPhone(hotelEntity.getHotelPhone());
        hotelResponseDto.setHotelPrice(hotelEntity.getHotelPrice());
        hotelResponseDto.setHotelEmail(hotelEntity.getHotelEmail()); // ADDED
        hotelResponseDto.setHotelCity(hotelEntity.getHotelCity());   // ADDED


        return hotelResponseDto;
    }


    private HotelUpdateResponseDto mapToUpdateDto (HotelEntity hotelEntity)
    {
        HotelUpdateResponseDto hotelUpdateResponseDto = new HotelUpdateResponseDto();
        hotelUpdateResponseDto.setHotelAddress(hotelEntity.getHotelAddress());
        hotelUpdateResponseDto.setHotelDescription(hotelEntity.getHotelDescription());
        hotelUpdateResponseDto.setHotelPhone(hotelEntity.getHotelPhone());
        hotelUpdateResponseDto.setHotelPrice(hotelEntity.getHotelPrice());

        return hotelUpdateResponseDto;

    }
}


