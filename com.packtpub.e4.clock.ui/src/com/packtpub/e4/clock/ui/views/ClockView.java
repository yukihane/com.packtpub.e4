package com.packtpub.e4.clock.ui.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import javax.inject.Inject;

/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ClockView extends ViewPart {
    private TableViewer viewer;
    private Action action1;
    private Action action2;
    private Action doubleClickAction;

    class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
        @Override
        public String getColumnText(final Object obj, final int index) {
            return getText(obj);
        }

        @Override
        public Image getColumnImage(final Object obj, final int index) {
            return getImage(obj);
        }

        @Override
        public Image getImage(final Object obj) {
            return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
        }
    }

    @Override
    public void createPartControl(final Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setInput(new String[] { "One", "Two", "Three" });
        viewer.setLabelProvider(new ViewLabelProvider());
        getSite().setSelectionProvider(viewer);
        makeActions();
        hookContextMenu();
        hookDoubleClickAction();
        contributeToActionBars();
    }

    private void hookContextMenu() {
        final MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(final IMenuManager manager) {
                ClockView.this.fillContextMenu(manager);
            }
        });
        final Menu menu = menuMgr.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, viewer);
    }

    private void contributeToActionBars() {
        final IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(final IMenuManager manager) {
        manager.add(action1);
        manager.add(new Separator());
        manager.add(action2);
    }

    private void fillContextMenu(final IMenuManager manager) {
        manager.add(action1);
        manager.add(action2);
        // Other plug-ins can contribute there actions here
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    private void fillLocalToolBar(final IToolBarManager manager) {
        manager.add(action1);
        manager.add(action2);
    }

    private void makeActions() {
        action1 = new Action() {
            @Override
            public void run() {
                showMessage("Action 1 executed");
            }
        };
        action1.setText("Action 1");
        action1.setToolTipText("Action 1 tooltip");
        action1.setImageDescriptor(
            PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

        action2 = new Action() {
            @Override
            public void run() {
                showMessage("Action 2 executed");
            }
        };
        action2.setText("Action 2");
        action2.setToolTipText("Action 2 tooltip");
        action2.setImageDescriptor(workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
        doubleClickAction = new Action() {
            @Override
            public void run() {
                final IStructuredSelection selection = viewer.getStructuredSelection();
                final Object obj = selection.getFirstElement();
                showMessage("Double-click detected on " + obj.toString());
            }
        };
    }

    private void hookDoubleClickAction() {
        viewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(final DoubleClickEvent event) {
                doubleClickAction.run();
            }
        });
    }

    private void showMessage(final String message) {
        MessageDialog.openInformation(
            viewer.getControl().getShell(),
            "Clock View",
            message);
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}
