package com.packtpub.e4.clock.ui.internal;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TimeZoneViewerFilter extends ViewerFilter {
    private final String pattern;

    public TimeZoneViewerFilter(final String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean select(final Viewer v, final Object parent, final Object element) {
        if (element instanceof ZoneId) {
            final ZoneId zone = (ZoneId) element;
            final String displayName = zone.getDisplayName(TextStyle.FULL, Locale.getDefault());
            return displayName.contains(pattern);
        } else {
            return true;
        }
    }
}
