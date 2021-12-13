package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class TimeZoneView extends ViewPart {

    @Override
    public void createPartControl(final Composite parent) {
        final Map<String, Set<ZoneId>> timeZones = TimeZoneComparator.getTimeZones();
        final CTabFolder tabs = new CTabFolder(parent, SWT.BOTTOM);
        timeZones.forEach((region, zones) -> {
            final CTabItem item = new CTabItem(tabs, SWT.NONE);
            item.setText(region);
        });
        tabs.setSelection(0);
    }

    @Override
    public void setFocus() {
    }

}
