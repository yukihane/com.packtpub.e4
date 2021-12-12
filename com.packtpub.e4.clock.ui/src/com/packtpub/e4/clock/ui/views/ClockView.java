package com.packtpub.e4.clock.ui.views;

import java.time.LocalTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ClockView extends ViewPart {
    @Override
    public void createPartControl(final Composite parent) {
        final Canvas clock = new Canvas(parent, SWT.NONE);
        clock.addPaintListener(this::drawClock);
    }

    private void drawClock(final PaintEvent e) {
        e.gc.drawArc(e.x, e.y, e.width - 1, e.height - 1, 0, 360);
        final int seconds = LocalTime.now().getSecond();
        final int arc = (15 - seconds) * 6 % 360;
        final Color blue = e.display.getSystemColor(SWT.COLOR_BLUE);
        e.gc.setBackground(blue);
        e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 1, 2);
    }

    @Override
    public void setFocus() {
    }
}
