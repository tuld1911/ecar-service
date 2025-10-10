package com.ecar.ecarservice.service.admin;

import com.ecar.ecarservice.enums.AppRole;
import com.ecar.ecarservice.enums.BookingStatus;
import com.ecar.ecarservice.repositories.AppUserRepository;
import com.ecar.ecarservice.repositories.BookingRepository;
import com.ecar.ecarservice.repositories.ServiceRecordRepository;
import com.ecar.ecarservice.web.dto.admin.ActivityDto;
import com.ecar.ecarservice.web.dto.admin.AdminOverviewDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class AdminDashboardService {

    private final AppUserRepository userRepo;
    private final BookingRepository bookingRepo;
    private final ServiceRecordRepository serviceRecordRepo;

    public AdminDashboardService(
            AppUserRepository userRepo,
            BookingRepository bookingRepo,
            ServiceRecordRepository serviceRecordRepo
    ) {
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
        this.serviceRecordRepo = serviceRecordRepo;
    }

    public AdminOverviewDto overview() {
        long customers = userRepo.countByRolesContaining(AppRole.CUSTOMER);
        long vehicles  = 0; // chưa có Vehicle entity

        long openAppts = bookingRepo.countByStatusIn(
                List.of(BookingStatus.PENDING, BookingStatus.CONFIRMED)
        );

        long inProgress = serviceRecordRepo.countByDetailStatus("IN_PROGRESS");

        YearMonth ym = YearMonth.now();
        LocalDateTime start = ym.atDay(1).atStartOfDay();
        LocalDateTime end   = ym.plusMonths(1).atDay(1).atStartOfDay();
        double monthRevenue = serviceRecordRepo.sumPaidInRange(start, end);

        var exchange = List.of(
                new AdminOverviewDto.SeriesPoint("Mar", 14.1),
                new AdminOverviewDto.SeriesPoint("Apr", 14.8),
                new AdminOverviewDto.SeriesPoint("May", 15.0),
                new AdminOverviewDto.SeriesPoint("Jun", 14.6)
        );
        var lastCosts = List.of(
                new AdminOverviewDto.CategoryValue("Mar", 800),
                new AdminOverviewDto.CategoryValue("Apr", 1200),
                new AdminOverviewDto.CategoryValue("May", 600),
                new AdminOverviewDto.CategoryValue("Jun", 1900)
        );
        var eff = List.of(
                new AdminOverviewDto.CategoryValue("Earned", 5200),
                new AdminOverviewDto.CategoryValue("Spend", 4700)
        );

        return new AdminOverviewDto(customers, vehicles, openAppts, inProgress,
                monthRevenue, exchange, lastCosts, eff);
    }

    public List<ActivityDto> recentActivities() {
        return List.of(
                new ActivityDto("Today • 09:12", "To Philip Kelley", -450.51, "Sent"),
                new ActivityDto("Today • 08:40", "From Philip Kelley", +450.51, "Received"),
                new ActivityDto("Yesterday • 17:05", "Starbucks", -5.00, "Cafe")
        );
    }
}
