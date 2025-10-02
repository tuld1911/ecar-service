package com.ecar.ecarservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponseDto {
    private Long id;
    private String carType;
    private String pickUpLocation;
    private String dropOffLocation;
    private LocalDate pickUpDate;
    private String pickUpTime;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
