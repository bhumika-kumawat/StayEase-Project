package com.stayEase.stayEase.HotelService;

import com.stayEase.stayEase.HotelEntity.HotelEntity;
import com.stayEase.stayEase.HotelRepo.HotelRepo;
import com.stayEase.stayEase.HotelRequestDto.HotelRequestDTO;
import com.stayEase.stayEase.HotelResponseDto.HotelResponseDto;
import com.stayEase.stayEase.UpdateReqDto.UpdateReqDto;
import com.stayEase.stayEase.UpdateResponseDto.UpdateResponseDto;
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

    public HotelResponseDto getHotel(Long id)
    {
       Optional<HotelEntity> existHotel =  hotelRepo.findById(id);

       if(existHotel.isEmpty())
       {
           return null;
       }

       HotelEntity existingHotel = existHotel.get();
       return mapToDto(existingHotel);

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

    public UpdateResponseDto update(UpdateReqDto updateReqHotel , Long id)
    {

        Optional<HotelEntity> existingRecord = hotelRepo.findById(id);

        if(existingRecord.isEmpty())
        {
            return null;

        }


        HotelEntity existingHotel= existingRecord.get();
        existingHotel.setHotelAddress(updateReqHotel.getHotelAddress());
        existingHotel.setHotelDescription(updateReqHotel.getHotelDescription());
        existingHotel.setHotelPhone(updateReqHotel.getHotelPhone());
        existingHotel.setHotelPrice(updateReqHotel.getHotelPrice());

        HotelEntity savedHotel =  hotelRepo.save(existingHotel);
        return mapToUpdateDto(savedHotel);
    }


//    delete

    public boolean deleteHotel(Long id) {
        if (!hotelRepo.existsById(id)) {
            return false;
        }

        hotelRepo.deleteById(id);
        return true;
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

        return hotelResponseDto;
    }

    private UpdateResponseDto mapToUpdateDto (HotelEntity hotelEntity)
    {
        UpdateResponseDto updateResponseDto = new UpdateResponseDto();
        updateResponseDto.setHotelAddress(hotelEntity.getHotelAddress());
        updateResponseDto.setHotelDescription(hotelEntity.getHotelDescription());
        updateResponseDto.setHotelPhone(hotelEntity.getHotelPhone());
        updateResponseDto.setHotelPrice(hotelEntity.getHotelPrice());

        return updateResponseDto;

    }
}


