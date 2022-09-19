package com.holiday.demo.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayDetails {

    private String name;
    private LocalDate date;
    private String country;
}
