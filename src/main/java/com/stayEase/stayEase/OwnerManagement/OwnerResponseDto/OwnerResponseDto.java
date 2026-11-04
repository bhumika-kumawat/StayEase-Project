package com.stayEase.stayEase.OwnerManagement.OwnerResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class OwnerResponseDto {


    private String ownerName;
    private String email;
    private String phoneNo;
    private LocalDateTime createdAt;


}
