package com.holiday.demo.core.mapper;

import com.holiday.demo.client.model.Holiday;
import com.holiday.demo.core.dto.HolidayDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HolidayMapper {

    List<HolidayDetails> mapHolidayDetails(List<Holiday> holidayList);

    @Mapping(source = "date", target = "nearestHolidayDate")
    @Mapping(source = "name", target = "holidayName")
    com.holiday.demo.model.Holiday mapHoliday(HolidayDetails holidayDetails);
}
