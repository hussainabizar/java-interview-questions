package com.holiday.demo.core.provider;

import com.holiday.demo.client.api.HolidaysApi;
import com.holiday.demo.client.model.Holidays;
import com.holiday.demo.core.dto.HolidayDetails;
import com.holiday.demo.core.mapper.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class HolidayClientProvider {

    @Value("${backbase.holiday_client_key}")
    private String apiKey;

    HolidaysApi holidaysApi;
    HolidayMapper holidayMapper;

    @Autowired
    HolidayClientProvider(HolidaysApi holidaysApi, HolidayMapper holidayMapper){
        this.holidaysApi = holidaysApi;
        this.holidayMapper = holidayMapper;
    }

    public List<HolidayDetails> getHolidays(String countryCode, int year){
        try {
            Holidays holidays = holidaysApi.getHolidays(UUID.fromString(apiKey), countryCode, year, null, null,
                    null, true, null, null, null, null, null, null);
            return holidayMapper.mapHolidayDetails(holidays.getHolidays());
        }catch (Exception ex){
            return null;
        }
    }

}
