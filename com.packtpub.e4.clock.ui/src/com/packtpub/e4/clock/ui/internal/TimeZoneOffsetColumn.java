package com.packtpub.e4.clock.ui.internal;

import java.time.Instant;
import java.time.ZoneId;
import org.eclipse.swt.SWT;

public class TimeZoneOffsetColumn extends TimeZoneColumn {
    @Override
    public String getText(final Object element) {
        if (element instanceof ZoneId) {
            return ((ZoneId) element).getRules().getOffset(Instant.now()).toString();
        } else {
            return "";
        }
    }

    @Override
    public String getTitle() {
        return "Offset";
    }

    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getAlignment() {
        return SWT.RIGHT;
    }
}
