package com.packtpub.e4.clock.ui;

import java.time.LocalTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas {
    public ClockWidget(final Composite parent, final int style) {
        super(parent, style);
        addPaintListener(this::drawClock);
        final Runnable redraw = () -> {
            while (!this.isDisposed()) {
                this.getDisplay().asyncExec(() -> this.redraw());
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    return;
                }

            }
        };
        new Thread(redraw, "TickTock").start();
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
    public Point computeSize(final int w, final int h, final boolean changed) {
        int size;
        if (w == SWT.DEFAULT) {
            size = h;
        } else if (h == SWT.DEFAULT) {
            size = w;
        } else {
            size = Math.min(w, h);
        }
        if (size == SWT.DEFAULT) {
            size = 50;
        }
        return new Point(size, size);
    }
}
