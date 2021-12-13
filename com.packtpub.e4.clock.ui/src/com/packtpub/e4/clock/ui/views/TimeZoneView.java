package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.ClockWidget;
import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.ViewPart;

public class TimeZoneView extends ViewPart {

    @Override
    public void createPartControl(final Composite parent) {
        final Map<String, Set<ZoneId>> timeZones = TimeZoneComparator.getTimeZones();
        final CTabFolder tabs = new CTabFolder(parent, SWT.BOTTOM);
        timeZones.forEach((region, zones) -> {
            final CTabItem item = new CTabItem(tabs, SWT.NONE);
            item.setText(region);
            final ScrolledComposite scrolled = new ScrolledComposite(tabs, SWT.H_SCROLL | SWT.V_SCROLL);
            final Composite clocks = new Composite(scrolled, SWT.NONE);
            clocks.setBackground(clocks.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
            item.setControl(scrolled);
            scrolled.setContent(clocks);
            clocks.setLayout(new RowLayout());
            final RGB rgb = new RGB(128, 128, 128);
            zones.forEach(zone -> {
                final Group group = new Group(clocks, SWT.SHADOW_ETCHED_IN);
                group.setText(zone.getId().split("/")[1]);
                group.setLayout(new FillLayout());
                new ClockWidget(group, SWT.NONE, rgb).setZone(zone);
            });
            final Point size = clocks.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            scrolled.setMinSize(size);
            scrolled.setExpandHorizontal(true);
            scrolled.setExpandVertical(true);
        });
        tabs.setSelection(0);
    }

    @Override
    public void setFocus() {
    }

}
