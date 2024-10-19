package com.isteyft.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FlexibleDateDeserializer extends JsonDeserializer<Date> {

    private static final String[] DATE_FORMATS = new String[]{
            "yyyy年MM月dd日 HH:mm",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd",
            "EEE, dd MMM yyyy HH:mm:ss zzz",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy.MM.dd HH:mm:ss"
    };

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getValueAsString();
        for (String dateFormat : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(dateFormat, Locale.CHINA).parse(dateStr);
            } catch (ParseException e) {
                // Ignore and try the next format
            }
        }
        throw ctxt.weirdStringException(dateStr, Date.class, "Unparseable date: \"" + dateStr + "\"");
    }
}