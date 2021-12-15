package com.packtpub.e4.clock.ui.internal;

import com.packtpub.e4.clock.ui.ClockWidget;
import java.time.ZoneId;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class TimeZoneDialog extends MessageDialog {
    private final ZoneId timeZone;

    public TimeZoneDialog(final Shell parentShell, final ZoneId timeZone) {
        super(parentShell, timeZone.getId(), null, "Time Zone " + timeZone.getId(), INFORMATION,
            new String[] { IDialogConstants.OK_LABEL }, 0);
        this.timeZone = timeZone;
    }

    @Override
    protected Control createCustomArea(final Composite parent) {
        final ClockWidget clock = new ClockWidget(parent, SWT.NONE, new RGB(128, 255, 0));
        clock.setZone(timeZone);
        return parent;
    }
}
