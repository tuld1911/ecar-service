package com.ecar.ecarservice.repositories;

import com.ecar.ecarservice.enitiies.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByLicensePlateOrderByServiceDateDesc(String licensePlate);

    // Đếm số ServiceRecord có bất kỳ detail nào đang ở trạng thái chỉ định
    @Query("""
        SELECT COUNT(DISTINCT s.id)
        FROM ServiceRecord s
        JOIN s.details d
        WHERE d.status = :status
    """)
    long countByDetailStatus(@Param("status") String status);

    // Tổng tiền đã trả trong khoảng [start, end) dựa trên chi tiết (cost)
    @Query("""
        SELECT COALESCE(SUM(d.cost), 0)
        FROM ServiceRecordDetail d
        WHERE d.serviceRecord.createdAt >= :start
          AND d.serviceRecord.createdAt <  :end
    """)
    double sumPaidInRange(@Param("start") LocalDateTime start,
                          @Param("end")   LocalDateTime end);
}
