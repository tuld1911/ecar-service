package com.ecar.ecarservice.repositories;

import com.ecar.ecarservice.enitiies.Booking;
import com.ecar.ecarservice.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Đếm theo danh sách trạng thái (ENUM)
    long countByStatusIn(Collection<BookingStatus> statuses);

    // Lấy booking theo user id (dùng trong BookingServiceImpl.getBookingsForCurrentUser)
    List<Booking> findByUserId(Long userId);
}
