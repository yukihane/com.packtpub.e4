package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.ClockWidget;
import java.time.ZoneId;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ClockView extends ViewPart {
    private Combo timeZones;

    @Override
    public void createPartControl(final Composite parent) {
        final Object[] objects = parent.getDisplay().getDeviceData().objects;
        int count = 0;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Color) {
                count++;
            }
        }
        System.err.println("There are " + count + " Color instances");
        final RowLayout layout = new RowLayout(SWT.HORIZONTAL);
        parent.setLayout(layout);
        final ClockWidget clock1 = new ClockWidget(parent, SWT.NONE, new RGB(255, 0, 0));
        final ClockWidget clock2 = new ClockWidget(parent, SWT.NONE, new RGB(0, 255, 0));
        final ClockWidget clock3 = new ClockWidget(parent, SWT.NONE, new RGB(0, 0, 255));
        clock1.setLayoutData(new RowData(20, 20));
        clock2.setLayoutData(new RowData(50, 50));
        clock3.setLayoutData(new RowData(100, 100));
        timeZones = new Combo(parent, SWT.DROP_DOWN);
        timeZones.setVisibleItemCount(5);
        for (final String zone : ZoneId.getAvailableZoneIds()) {
            timeZones.add(zone);
        }
        timeZones.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String id = timeZones.getText();
                clock3.setZone(ZoneId.of(id));
                clock3.redraw();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                clock3.setZone(ZoneId.systemDefault());
                clock3.redraw();
            }
        });
    }

    @Override
    public void setFocus() {
        timeZones.setFocus();
    }
}
