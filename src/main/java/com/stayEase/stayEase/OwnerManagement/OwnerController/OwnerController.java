package com.stayEase.stayEase.OwnerManagement.OwnerController;

import com.stayEase.stayEase.OwnerManagement.OwnerRequestDto.OwnerRequestDto;
import com.stayEase.stayEase.OwnerManagement.OwnerResponseDto.OwnerResponseDto;
import com.stayEase.stayEase.OwnerManagement.OwnerService.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/hotelOwner")
public class OwnerController {

    OwnerService ownerService;
    OwnerController (OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }


     //   create owner
    @PostMapping
    public ResponseEntity<OwnerResponseDto>create(@Valid  @RequestBody OwnerRequestDto ownerRequestDto)
    {
        OwnerResponseDto createdOwner = ownerService.createOwner(ownerRequestDto);
        return ResponseEntity.ok(createdOwner);

    }

    // Get owner by ID
    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResponseDto> getOwnerById(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(ownerService.getOwnerById(ownerId));
    }

    // Get all owners
    @GetMapping
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {

        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    //  update Owner
    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerResponseDto>updateOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto ,
                                                       @PathVariable Long ownerId)
    {
        OwnerResponseDto updatedOwner = ownerService.updateOwner(ownerRequestDto, ownerId);
        return ResponseEntity.ok(updatedOwner);

    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long ownerId) {

        ownerService.deleteOwner(ownerId);

        return ResponseEntity.noContent().build();
    }


}
