package com.ecar.ecarservice.service;

import com.ecar.ecarservice.dto.BookingRequestDto;
import com.ecar.ecarservice.dto.BookingResponseDto;
import com.ecar.ecarservice.enitiies.BookAppointment;
import com.ecar.ecarservice.repositories.BookAppointmentRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookAppointmentServiceImpl implements BookAppointmentService {

    private final BookAppointmentRepository bookingRepository;
    private final AuditorAware<String> auditorAware;

    public BookAppointmentServiceImpl(BookAppointmentRepository bookingRepository, AuditorAware<String> auditorAware) {
        this.bookingRepository = bookingRepository;
        this.auditorAware = auditorAware;
    }

    @Override
    public BookingResponseDto createAppointment(BookingRequestDto requestDto) {
        // Gọi hàm mapper để chuyển đổi
        BookAppointment appointment = convertToEntity(requestDto);

        BookAppointment savedAppointment = bookingRepository.save(appointment);
        return convertToResponseDto(savedAppointment);
    }

    @Override
    public List<BookingResponseDto> getAllAppointments() {
        return bookingRepository.findAll().stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDto> getMyAppointments() {
        String currentUserEmail = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new IllegalStateException("Cannot determine current user"));
        return bookingRepository.findByCreatedBy(currentUserEmail).stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(Long id) {
        bookingRepository.deleteById(id);
    }

    // --- HÀM MAPPER ĐÃ ĐƯỢC HOÀN THIỆN ---

    // Chuyển từ Request DTO sang Entity
    private BookAppointment convertToEntity(BookingRequestDto dto) {
        BookAppointment entity = new BookAppointment();
        entity.setCarType(dto.getCarType());
        entity.setPickUpLocation(dto.getPickUpLocation());
        entity.setDropOffLocation(dto.getDropOffLocation());
        entity.setPickUpDate(dto.getPickUpDate());
        entity.setPickUpTime(dto.getPickUpTime());
        entity.setCustomerName(dto.getCustomerName());
        entity.setCustomerPhone(dto.getCustomerPhone());
        entity.setCustomerEmail(dto.getCustomerEmail());
        return entity;
    }

    // Chuyển từ Entity sang Response DTO
    private BookingResponseDto convertToResponseDto(BookAppointment entity) {
        BookingResponseDto dto = new BookingResponseDto();
        dto.setId(entity.getId());
        dto.setCarType(entity.getCarType());
        dto.setPickUpLocation(entity.getPickUpLocation());
        dto.setDropOffLocation(entity.getDropOffLocation());
        dto.setPickUpDate(entity.getPickUpDate());
        dto.setPickUpTime(entity.getPickUpTime());
        dto.setCustomerName(entity.getCustomerName());
        dto.setCustomerPhone(entity.getCustomerPhone());
        dto.setCustomerEmail(entity.getCustomerEmail());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setUpdatedBy(entity.getUpdatedBy());
        return dto;
    }
}
