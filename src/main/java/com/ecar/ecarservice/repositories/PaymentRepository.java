package com.ecar.ecarservice.repositories;

import com.ecar.ecarservice.enitiies.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM Payment p
        WHERE (:onlyPaid = false OR p.status = 'PAID')
          AND p.createdAt >= :start AND p.createdAt < :end
    """)
    double sumPaidInRange(@Param("start") LocalDateTime start,
                          @Param("end")   LocalDateTime end,
                          @Param("onlyPaid") boolean onlyPaid);
}
