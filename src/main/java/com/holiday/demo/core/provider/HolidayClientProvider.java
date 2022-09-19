package com.holiday.demo.core.provider;

import com.holiday.demo.client.api.HolidaysApi;
import com.holiday.demo.client.model.Holidays;
import com.holiday.demo.core.dto.HolidayDetails;
import com.holiday.demo.core.mapper.HolidayMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HolidayClientProvider {

    HolidaysApi holidaysApi;
    HolidayMapper holidayMapper;

    public List<HolidayDetails> getHolidays(String countryCode, LocalDate inputDate){
        try {
            Holidays holidays = holidaysApi.getHolidays(UUID.fromString("81fbe33f-28b5-4754-b98c-4bf5a6595637"), countryCode, inputDate.getYear(), null, null,
                    null, true, null, null, null, null, null, null);
            return holidayMapper.mapHolidayDetails(holidays.getHolidays());
        }catch (Exception ex){
            return null;
        }
    }

}
