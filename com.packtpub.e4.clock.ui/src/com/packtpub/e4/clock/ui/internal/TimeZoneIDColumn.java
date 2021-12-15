package com.packtpub.e4.clock.ui.internal;

import java.time.ZoneId;

public class TimeZoneIDColumn extends TimeZoneColumn {
    @Override
    public String getText(final Object element) {
        if (element instanceof ZoneId) {
            return ((ZoneId) element).getId();
        } else {
            return "";
        }
    }

    @Override
    public String getTitle() {
        return "ID";
    }
}
