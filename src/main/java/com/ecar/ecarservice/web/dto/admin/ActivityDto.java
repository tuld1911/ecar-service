package com.ecar.ecarservice.web.dto.admin;

public record ActivityDto(
        String timeLabel,   // "Today 10:15"
        String title,       // "WO #123 to Philip Kelley"
        double amount,      // +450.51 / -5.00
        String category     // "Sent","Cafe",...
) {}
