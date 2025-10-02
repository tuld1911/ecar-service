package com.ecar.ecarservice.service;

import com.ecar.ecarservice.dto.BookingRequestDto;
import com.ecar.ecarservice.dto.BookingResponseDto;

import java.util.List;

public interface BookAppointmentService {
    BookingResponseDto createAppointment(BookingRequestDto requestDto);
    List<BookingResponseDto> getAllAppointments(); // Cho Admin
    List<BookingResponseDto> getMyAppointments();   // Cho user thường
    void deleteAppointment(Long id);
}
