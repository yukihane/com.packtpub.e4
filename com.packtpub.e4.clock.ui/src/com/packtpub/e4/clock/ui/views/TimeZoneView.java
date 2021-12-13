package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.ClockWidget;
import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
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
            final Composite clocks = new Composite(tabs, SWT.NONE);
            clocks.setLayout(new RowLayout());
            item.setControl(clocks);
            final RGB rgb = new RGB(128, 128, 128);
            zones.forEach(zone -> {
                new ClockWidget(clocks, SWT.NONE, rgb).setZone(zone);
            });
        });
        tabs.setSelection(0);
    }

    @Override
    public void setFocus() {
    }

}
