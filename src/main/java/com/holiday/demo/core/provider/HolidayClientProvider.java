package com.holiday.demo.core.provider;

import com.holiday.demo.client.api.HolidaysApi;
import com.holiday.demo.client.model.Holidays;
import com.holiday.demo.core.dto.HolidayDetails;
import com.holiday.demo.core.exception.ErrorManager;
import com.holiday.demo.core.exception.ProviderException;
import com.holiday.demo.core.mapper.HolidayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class HolidayClientProvider {

    @Value("${backbase.holiday_client_key}")
    private String apiKey;

    HolidaysApi holidaysApi;
    HolidayMapper holidayMapper;
    ErrorManager errorManager;

    @Autowired
    HolidayClientProvider(HolidaysApi holidaysApi, HolidayMapper holidayMapper, ErrorManager errorManager){
        this.holidaysApi = holidaysApi;
        this.holidayMapper = holidayMapper;
        this.errorManager = errorManager;
    }

    public List<HolidayDetails> getHolidays(String requestId, String countryCode, int year) throws ProviderException{
        try {
            Holidays holidays = holidaysApi.getHolidays(UUID.fromString(apiKey), countryCode, year, null, null,
                    null, true, null, null, null, null, null, null);
            return holidayMapper.mapHolidayDetails(holidays.getHolidays());
        }catch (Exception ex){
            throw errorManager.handleHolidayApiError.apply(ex, requestId);
        }
    }
}
