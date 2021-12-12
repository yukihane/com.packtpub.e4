package com.packtpub.e4.clock.ui;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas {
    private final Color color;
    private ZoneId zone = ZoneId.systemDefault();

    public ClockWidget(final Composite parent, final int style, final RGB rgb) {
        super(parent, style);
        this.color = new Color(parent.getDisplay(), rgb);
        addDisposeListener(e -> color.dispose());
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
        final ZonedDateTime now = ZonedDateTime.now(zone);
        final int seconds = now.getSecond();
        int arc = (15 - seconds) * 6 % 360;
        e.gc.setBackground(color);
        e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 1, 2);
        e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
        final int hours = now.getHour();
        arc = (3 - hours) * 30 % 360;
        e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 5, 10);
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

    public void setZone(final ZoneId zone) {
        this.zone = zone;
    }

    // This does not work - add a dispose listener instead
    // @Override
    // public void dispose() {
    // if (color != null && !color.isDisposed())
    // color.dispose();
    // super.dispose();
    // }
}
