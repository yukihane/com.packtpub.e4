package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.ClockWidget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ClockView extends ViewPart {
    @Override public void createPartControl(final Composite parent) {
        final RowLayout layout = new RowLayout(SWT.HORIZONTAL);
        parent.setLayout(layout);
        final ClockWidget clock1 = new ClockWidget(parent, SWT.NONE, new RGB(255, 0, 0));
        final ClockWidget clock2 = new ClockWidget(parent, SWT.NONE, new RGB(0, 255, 0));
        final ClockWidget clock3 = new ClockWidget(parent, SWT.NONE, new RGB(0, 0, 255));
        clock1.setLayoutData(new RowData(20, 20));
        clock2.setLayoutData(new RowData(50, 50));
        clock3.setLayoutData(new RowData(100, 100));
    }

    @Override public void setFocus() {
    }
}
