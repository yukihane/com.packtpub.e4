package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.internal.TimeZoneDisplayNameColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneIDColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneOffsetColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneSummerTimeColumn;
import java.time.ZoneId;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TimeZoneTableView {
    private TableViewer tableViewer;

    @PostConstruct
    public void create(final Composite parent) {
        tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
        tableViewer.getTable().setHeaderVisible(true);
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        new TimeZoneIDColumn().addColumnTo(tableViewer);
        new TimeZoneDisplayNameColumn().addColumnTo(tableViewer);
        new TimeZoneOffsetColumn().addColumnTo(tableViewer);
        new TimeZoneSummerTimeColumn().addColumnTo(tableViewer);
        tableViewer.setInput(ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).toArray());
    }

    @Focus
    public void focus() {
        tableViewer.getControl().setFocus();
    }

    @Inject
    @Optional
    public void setTimeZone(@Named(IServiceConstants.ACTIVE_SELECTION) final ZoneId timeZone) {
        if (timeZone != null && tableViewer != null) {
            tableViewer.setSelection(new StructuredSelection(timeZone));
            tableViewer.reveal(timeZone);
        }
    }
}
