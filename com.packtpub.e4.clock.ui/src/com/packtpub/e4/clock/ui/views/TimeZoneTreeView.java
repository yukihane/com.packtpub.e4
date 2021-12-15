package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TimeZoneTreeView {
    private TreeViewer treeViewer;

    @PostConstruct
    public void create(final Composite parent) {
        treeViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
        treeViewer.setLabelProvider(new TimeZoneLabelProvider());
        treeViewer.setContentProvider(new TimeZoneContentProvider());
        treeViewer.setInput(new Object[] { TimeZoneComparator.getTimeZones() });
    }

    @Focus
    public void focus() {
        treeViewer.getControl().setFocus();
    }
}
