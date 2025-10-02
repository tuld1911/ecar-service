package com.ecar.ecarservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestDto {
    private String carType;
    private String pickUpLocation;
    private String dropOffLocation;
    private LocalDate pickUpDate;
    private String pickUpTime;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
}
