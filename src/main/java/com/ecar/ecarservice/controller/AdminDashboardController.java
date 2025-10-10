package com.ecar.ecarservice.controller;

import com.ecar.ecarservice.service.admin.AdminDashboardService;
import com.ecar.ecarservice.web.dto.admin.ActivityDto;
import com.ecar.ecarservice.web.dto.admin.AdminOverviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {
    private final AdminDashboardService svc;
    public AdminDashboardController(AdminDashboardService svc){ this.svc = svc; }

    @GetMapping("/overview")
    public AdminOverviewDto overview(){ return svc.overview(); }

    @GetMapping("/activities")
    public List<ActivityDto> activities(){ return svc.recentActivities(); }
}
