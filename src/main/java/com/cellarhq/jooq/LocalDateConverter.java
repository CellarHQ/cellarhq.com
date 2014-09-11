package com.cellarhq.jooq;

import org.jooq.Converter;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateConverter implements Converter<Date, LocalDate> {

    @Override
    public LocalDate from(Date databaseObject) {
        if (databaseObject != null) {
            return databaseObject.toLocalDate();
        }

        return null;
    }

    @Override
    public Date to(LocalDate userObject) {
        return Date.valueOf(userObject);
    }

    @Override
    public Class<Date> fromType() {
        return Date.class;
    }

    @Override
    public Class<LocalDate> toType() {
        return LocalDate.class;
    }
}
