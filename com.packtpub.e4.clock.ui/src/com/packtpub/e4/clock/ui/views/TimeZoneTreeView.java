package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import com.packtpub.e4.clock.ui.internal.TimeZoneDialog;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerComparator;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerFilter;
import java.net.URL;
import java.time.ZoneId;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;

public class TimeZoneTreeView {
    private TreeViewer treeViewer;
    @Inject
    private ISharedImages images;

    @PostConstruct
    public void create(final Composite parent) {
        final ResourceManager rm = JFaceResources.getResources();
        final LocalResourceManager lrm = new LocalResourceManager(rm, parent);
        final ImageRegistry ir = new ImageRegistry(lrm);
        final FontRegistry fr = new FontRegistry();
        final URL sample = getClass().getResource("/icons/sample.png");
        ir.put("sample", ImageDescriptor.createFromURL(sample));
        treeViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
        treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new TimeZoneLabelProvider(images, ir, fr)));
        treeViewer.setContentProvider(new TimeZoneContentProvider());
        treeViewer.setInput(new Object[] { TimeZoneComparator.getTimeZones() });
        treeViewer.setData("REVERSE", Boolean.TRUE);
        treeViewer.setComparator(new TimeZoneViewerComparator());
        treeViewer.setFilters(new ViewerFilter[] { new TimeZoneViewerFilter("GMT") });
        treeViewer.setExpandPreCheckFilters(true);
        treeViewer.addDoubleClickListener(event -> {
            final Viewer viewer = event.getViewer();
            final Shell shell = viewer.getControl().getShell();
            final ISelection sel = viewer.getSelection();
            Object selectedValue;
            if (!(sel instanceof IStructuredSelection) || sel.isEmpty()) {
                selectedValue = null;
            } else {
                selectedValue = ((IStructuredSelection) sel).getFirstElement();
            }
            if (selectedValue instanceof ZoneId) {
                final ZoneId timeZone = (ZoneId) selectedValue;
                // MessageDialog.openInformation(shell, timeZone.getId(), timeZone.toString());
                new TimeZoneDialog(shell, timeZone).open();
            }
        });
    }

    @Focus
    public void focus() {
        treeViewer.getControl().setFocus();
    }
}
