package com.ecar.ecarservice.controller;

import com.ecar.ecarservice.dto.BookingRequestDto;
import com.ecar.ecarservice.dto.BookingResponseDto;
import com.ecar.ecarservice.service.BookAppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookAppointmentController {

    private final BookAppointmentService bookingService;

    public BookAppointmentController(BookAppointmentService bookingService) {
        this.bookingService = bookingService;
    }

    // API cho bất kỳ ai đã đăng nhập để tạo lịch hẹn
    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto requestDto) {
        return new ResponseEntity<>(bookingService.createAppointment(requestDto), HttpStatus.CREATED);
    }

    // API cho user xem lịch hẹn của chính họ
    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponseDto>> getMyAppointments() {
        return ResponseEntity.ok(bookingService.getMyAppointments());
    }

    // --- API cho Admin ---
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')") // Phân quyền ở cấp độ phương thức
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllAppointments());
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
