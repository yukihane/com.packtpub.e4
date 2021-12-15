package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.internal.TimeZoneDisplayNameColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneIDColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneOffsetColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneSummerTimeColumn;
import java.time.ZoneId;
import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
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
}
