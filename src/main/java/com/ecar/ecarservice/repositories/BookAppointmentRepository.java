package com.ecar.ecarservice.repositories;

import com.ecar.ecarservice.enitiies.BookAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAppointmentRepository extends JpaRepository<BookAppointment, Long> {
    // Tìm các lịch hẹn của một người dùng cụ thể (dựa vào email đã lưu ở createdBy)
    List<BookAppointment> findByCreatedBy(String email);
}
