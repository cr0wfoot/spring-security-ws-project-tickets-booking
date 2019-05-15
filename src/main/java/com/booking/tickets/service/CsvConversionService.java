package com.booking.tickets.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Splitter.on;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class CsvConversionService {

    private static final String ROW_DELIMITER = ";";
    private static final String DETAILS_DELIMITER = ",";
    private static final String KEY_VALUE_DELIMITER = "=";

    public List<Map<String, String>> convertStringToListOfValues(final String stringWithData) {
        final List<Map<String, String>> values = new ArrayList<>();
        for (String trimmedString : stringWithData.replace("\n", EMPTY).replace("\r", EMPTY).split(ROW_DELIMITER)) {
            Map<String, String> mapWithValues = on(DETAILS_DELIMITER).withKeyValueSeparator(KEY_VALUE_DELIMITER).split(trimmedString);
            values.add(mapWithValues);
        }
        return values;
    }
}
