package com.holiday.demo.rest.controller;

import com.holiday.demo.core.service.HolidayService;
import com.holiday.demo.model.Holiday;
import com.holiday.demo.rest.representation.DemoApi;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/backbase/v1")
public class HolidayController implements DemoApi {

    HolidayService holidayService;

    @Override
    public ResponseEntity<Holiday> getClosestHoliday(String countryCode, LocalDate inputDate){
        return new ResponseEntity<>(holidayService.getNearestHoliday("xyz", countryCode, inputDate), HttpStatus.OK);
    }

}
