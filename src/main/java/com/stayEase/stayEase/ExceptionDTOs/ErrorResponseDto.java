package com.stayEase.stayEase.ExceptionDTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {

    private LocalDateTime time;
    private int statusCode;
    private String error;
    private String message;
    private String path;


}
