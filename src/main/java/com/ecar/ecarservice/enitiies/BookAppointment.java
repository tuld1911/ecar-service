package com.ecar.ecarservice.enitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "book_appointment")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Kích hoạt Auditing cho Entity này
public class BookAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Các trường thông tin từ màn hình Book Bảo dưỡng ---
    @Column(nullable = false)
    private String carType; // Loại xe (VD: "VW Golf VII")

    @Column(nullable = false)
    private String pickUpLocation; // Nơi lấy xe

    private String dropOffLocation; // Nơi trả xe (có thể null nếu giống nơi lấy)

    @Column(nullable = false)
    private LocalDate pickUpDate; // Ngày lấy xe

    @Column(nullable = false)
    private String pickUpTime; // Giờ lấy xe (VD: "12:00AM")

    // Giả sử có thêm các trường này
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    // --- 4 Trường theo dõi (Auditing Fields) ---
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy; // Sẽ lưu email của người tạo

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @LastModifiedBy
    private String updatedBy; // Sẽ lưu email của người cập nhật
}
