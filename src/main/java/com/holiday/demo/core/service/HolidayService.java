package com.holiday.demo.core.service;

import com.holiday.demo.core.dto.HolidayDetails;
import com.holiday.demo.core.mapper.HolidayMapper;
import com.holiday.demo.core.provider.HolidayClientProvider;
import com.holiday.demo.model.Holiday;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HolidayService {

    HolidayClientProvider holidayClientProvider;
    HolidayMapper holidayMapper;

    public Holiday getNearestHoliday(String requestId, String countryCode, LocalDate inputDate){
        List<HolidayDetails> holidayList  = holidayClientProvider.getHolidays(countryCode, inputDate);
        HolidayDetails nearestHoliday = holidayList.stream().filter(h -> h.getDate().isAfter(inputDate)).findFirst().get();
        return holidayMapper.mapHoliday(nearestHoliday);
    }
}
