package com.packtpub.e4.clock.ui.internal;

import java.time.Instant;
import java.time.ZoneId;
import org.eclipse.swt.SWT;

public class TimeZoneSummerTimeColumn extends TimeZoneColumn {
    @Override
    public String getText(final Object element) {
        if (element instanceof ZoneId) {
            return String.valueOf(!((ZoneId) element).getRules().getDaylightSavings(Instant.now()).isZero());
        } else {
            return "";
        }
    }

    @Override
    public String getTitle() {
        return "Summer Time";
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getAlignment() {
        return SWT.RIGHT;
    }
}
