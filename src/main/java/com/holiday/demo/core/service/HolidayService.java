package com.holiday.demo.core.service;

import com.holiday.demo.core.dto.HolidayDetails;
import com.holiday.demo.core.exception.HolidayApiException;
import com.holiday.demo.core.exception.ProviderException;
import com.holiday.demo.core.mapper.HolidayMapper;
import com.holiday.demo.core.provider.HolidayClientProvider;
import com.holiday.demo.model.Holiday;
import com.holiday.demo.model.SubError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class HolidayService {

    HolidayClientProvider holidayClientProvider;
    HolidayMapper holidayMapper;

    public Holiday getNearestHoliday(String requestId, String countryCode, Optional<LocalDate> inputDate){
        LocalDate date = inputDate.orElse(LocalDate.now().minusYears(1));
        log.info("{} HolidayService::getNearestHoliday. Checking the nearest holiday for date {} and country {}",
                requestId, date, countryCode);
        return findNextHoliday(requestId, countryCode, date);
    }

    private Holiday findNextHoliday(String requestId, String countryCode, LocalDate date) {
        try {
            List<HolidayDetails> holidayList  = holidayClientProvider.getHolidays(requestId, countryCode, date.getYear());
            Optional<HolidayDetails> nearestHoliday = holidayList.stream().filter(h -> h.getDate().isAfter(date)).findFirst();
            if(nearestHoliday.isPresent())
                return holidayMapper.mapHoliday(nearestHoliday.get());
            else{
                log.info("{} HolidayService::findNextHoliday. No holidays remaining in provided year, searching for first holiday of next year", requestId);
                //return findNextHoliday(requestId, countryCode, date.plusYears(1));
                return new Holiday();
            }
        } catch (ProviderException pe){
            throw new HolidayApiException(pe.getStatus(), "/backbase/holiday/error/client", "External API call failure",
                    new SubError(5002, "Holiday API call Failed"), pe.getErrorMessage());
        }
    }
}
