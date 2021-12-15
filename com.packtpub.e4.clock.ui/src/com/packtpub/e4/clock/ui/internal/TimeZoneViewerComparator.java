package com.packtpub.e4.clock.ui.internal;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class TimeZoneViewerComparator extends ViewerComparator {
    @Override
    public int compare(final Viewer viewer, final Object z1, final Object z2) {
        int compare;
        if (z1 instanceof ZoneId && z2 instanceof ZoneId) {
            final Instant now = Instant.now();
            final ZonedDateTime zdt1 = ZonedDateTime.ofInstant(now, (ZoneId) z1);
            final ZonedDateTime zdt2 = ZonedDateTime.ofInstant(now, (ZoneId) z2);
            compare = zdt1.compareTo(zdt2);
        } else {
            compare = z1.toString().compareTo(z2.toString());
        }
        final boolean reverse = Boolean.parseBoolean(String.valueOf(viewer.getData("REVERSE")));
        return reverse ? -compare : compare;
    }
}
