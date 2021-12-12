package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.ClockWidget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ClockView extends ViewPart {
    @Override public void createPartControl(final Composite parent) {
        new ClockWidget(parent, SWT.NONE);
    }

    @Override public void setFocus() {
    }
}
