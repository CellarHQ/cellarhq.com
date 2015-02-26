package com.cellarhq.common.handlebars.helpers;

import com.github.jknack.handlebars.Options;
import ratpack.handlebars.NamedHelper;
import ratpack.http.Request;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class BottledDateHelper  implements NamedHelper<Request> {

    public static final String HELPER_NAME = "bottledDate";

    @Override
    public String getName() {
        return HELPER_NAME;
    }

    @Override
    public CharSequence apply(Request context, Options options) throws IOException {
        LocalDate bottledDate = options.hash("date");

        if (bottledDate == null) {
            return "";
        }

        if (isFirstOfYear(bottledDate)) {
            return String.valueOf(bottledDate.getYear());
        }

        if (bottledDate.getDayOfMonth() == 1) {

            return bottledDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }

        return bottledDate.toString();
    }

    private boolean isFirstOfYear(LocalDate bottledDate) {
        return (bottledDate.getDayOfMonth() == 1 && bottledDate.getMonth() == Month.JANUARY);
    }
}
