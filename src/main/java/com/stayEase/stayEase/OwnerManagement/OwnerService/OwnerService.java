package com.stayEase.stayEase.OwnerManagement.OwnerService;

import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.OwnerManagement.OwnerEntity.OwnerEntity;
import com.stayEase.stayEase.OwnerManagement.OwnerRepo.OwnerRepo;
import com.stayEase.stayEase.OwnerManagement.OwnerRequestDto.OwnerRequestDto;
import com.stayEase.stayEase.OwnerManagement.OwnerResponseDto.OwnerResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OwnerService {

    OwnerRepo ownerRepo;

    OwnerService (OwnerRepo ownerRepo)
    {
        this.ownerRepo = ownerRepo;
    }

//create owner
    public OwnerResponseDto createOwner(OwnerRequestDto ownerRequestDto)
    {
        OwnerEntity ownerEntity = mapToOwnerEntity(ownerRequestDto);
        OwnerEntity savedOwner = ownerRepo.save(ownerEntity);
        return mapToOwnerDto(savedOwner);

    }

    // Get owner by ID
    public OwnerResponseDto getOwnerById(Long ownerId) {

        OwnerEntity ownerEntity = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Owner not found with ID: " + ownerId));

        return mapToOwnerDto(ownerEntity);
    }

    // Get all owners
    public List<OwnerResponseDto> getAllOwners() {

        return ownerRepo.findAll()
                .stream()
                .map(this::mapToOwnerDto)
                .toList();
    }

    //    update owner
    public OwnerResponseDto updateOwner(OwnerRequestDto ownerRequestDto , Long ownerId)
    {
         OwnerEntity existingOwner = ownerRepo.findById(ownerId).orElseThrow(
                 () -> new ResourceNotFoundException("Invalid Owner Id")
         );

         existingOwner.setPhoneNo(ownerRequestDto.getPhoneNo());
         existingOwner.setOwnerName(ownerRequestDto.getOwnerName());
         existingOwner.setEmail(ownerRequestDto.getEmail());

         OwnerEntity updatedOwner = ownerRepo.save(existingOwner);
         return mapToOwnerDto(updatedOwner);

    }

    // Delete owner by ID
    public void deleteOwner(Long ownerId) {

        OwnerEntity ownerEntity = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Owner not found with ID: " + ownerId));

        ownerRepo.delete(ownerEntity);
    }













//    method to convert DTO into Entity
    public OwnerEntity mapToOwnerEntity(OwnerRequestDto ownerRequestDto)
    {
        OwnerEntity ownerEntity =  new OwnerEntity();

        ownerEntity.setEmail(ownerRequestDto.getEmail());
        ownerEntity.setOwnerName(ownerRequestDto.getOwnerName());
        ownerEntity.setPhoneNo(ownerRequestDto.getPhoneNo());

        return ownerEntity;
    }


//    method to convert Entity into DTO
    public OwnerResponseDto mapToOwnerDto(OwnerEntity ownerEntity)
    {
        OwnerResponseDto ownerResponseDto =  new OwnerResponseDto();

        ownerResponseDto.setCreatedAt(ownerEntity.getCreatedAt());
        ownerResponseDto.setEmail(ownerEntity.getEmail());
        ownerResponseDto.setOwnerName(ownerEntity.getOwnerName());
        ownerResponseDto.setPhoneNo(ownerEntity.getPhoneNo());
        return ownerResponseDto;
    }
}
