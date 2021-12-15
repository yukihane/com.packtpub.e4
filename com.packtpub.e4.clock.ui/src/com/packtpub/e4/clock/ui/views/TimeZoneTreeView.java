package com.packtpub.e4.clock.ui.views;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
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
        final URL sample = getClass().getResource("/icons/sample.png");
        ir.put("sample", ImageDescriptor.createFromURL(sample));
        treeViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
        treeViewer.setLabelProvider(new TimeZoneLabelProvider(images, ir));
        treeViewer.setContentProvider(new TimeZoneContentProvider());
        treeViewer.setInput(new Object[] { TimeZoneComparator.getTimeZones() });
    }

    @Focus
    public void focus() {
        treeViewer.getControl().setFocus();
    }
}
