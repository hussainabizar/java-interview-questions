package com.holiday.demo.rest.controller;

import com.holiday.demo.core.service.HolidayService;
import com.holiday.demo.model.Holiday;
import com.holiday.demo.rest.representation.HolidayApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/backbase/v1")
public class HolidayController implements HolidayApi {

    HolidayService holidayService;

    @Override
    public ResponseEntity<Holiday> getClosestHoliday(String countryCode, String requestId, LocalDate inputDate){
        return new ResponseEntity<>(holidayService.getNearestHoliday(requestId, countryCode, Optional.ofNullable(inputDate)), HttpStatus.OK);
    }

}
