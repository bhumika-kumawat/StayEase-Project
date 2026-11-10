package com.stayEase.stayEase.Hotels.HotelService;

import com.stayEase.stayEase.CustomExceptions.DuplicateResourceFoundException;
import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.GlobalExceptionHandler.GlobalExceptionHandler;
import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import com.stayEase.stayEase.Hotels.HotelRepo.HotelRepo;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelRequestDTO;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelResponseDto;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelUpdateReqDto;
import com.stayEase.stayEase.Hotels.HotelDtos.HotelUpdateResponseDto;
import com.stayEase.stayEase.OwnerManagement.OwnerEntity.OwnerEntity;
import com.stayEase.stayEase.OwnerManagement.OwnerRepo.OwnerRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    HotelRepo hotelRepo;
    OwnerRepo ownerRepo;

    HotelService(HotelRepo hotelRepo , OwnerRepo ownerRepo) {
        this.hotelRepo = hotelRepo;
        this.ownerRepo = ownerRepo;
    }

//    create
    public HotelResponseDto createHotel(HotelRequestDTO hotelRequestDTO) {

        boolean emailExists = hotelRepo.existsByHotelEmail(hotelRequestDTO.getHotelEmail());

        if (emailExists) {
            throw new DuplicateResourceFoundException(
                    "Hotel with email "
                            + hotelRequestDTO.getHotelEmail()
                            + " already exists"
            );
        }

        if(hotelRepo.existsByhotelPhone(hotelRequestDTO.getHotelPhone()))
        {
            throw new DuplicateResourceFoundException(
                    "Hotel with phone no" + hotelRequestDTO.getHotelPhone()
                    + "already exists"
            );
        }
        OwnerEntity existOwnerId = ownerRepo.findById(hotelRequestDTO.getOwnerID()).orElseThrow(
                () -> new ResourceNotFoundException("Owner Id is not valid")
        );

        HotelEntity createdHotel = mapToEntity(hotelRequestDTO);

        createdHotel.setOwnerEntity(existOwnerId);

        HotelEntity savedHotel = hotelRepo.save(createdHotel);

        return mapToDto(savedHotel);
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

    public List<HotelResponseDto> getAllHotel(Long hotelOwnerId)
    {

        OwnerEntity existOwner = ownerRepo.findById(hotelOwnerId).orElseThrow(
                () -> new ResourceNotFoundException("owner id is not exist")
        );

        List<HotelEntity> existHotel = hotelRepo.findByOwnerEntity_Id(hotelOwnerId);

        return existHotel.stream()
                .map(this::mapToDto)
                .toList();
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


