package com.stayEase.stayEase.BookingManagement.BookingDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookingPageResponseDTO {

    private List<BookingResponseDTO> bookings;

    private int currentPage;

    private int pageSize;

    private long totalBookings;

    private int totalPages;

    private boolean hasNext;

    private boolean hasPrevious;
}