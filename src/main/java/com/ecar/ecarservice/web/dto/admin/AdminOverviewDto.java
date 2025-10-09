package com.ecar.ecarservice.web.dto.admin;


import java.util.List;
//record chỉ để chứa biến ,ko logic
public record AdminOverviewDto(
        long totalCustomers,
        long totalVehicles,
        long inProgressWorkOrders,
        long openAppointments,    // lịch hẹn đang chờ/xử lý

        double monthlyRevenue,    // doanh thu tháng hiện tại
        List<SeriesPoint> exchangeRates, // ví dụ chuỗi line demo
        List<CategoryValue> lastCosts,   // bar
        List<CategoryValue> efficiency   // donut
) {
    public record SeriesPoint(String label, double value) {}
    public record CategoryValue(String label, double value) {}
}
