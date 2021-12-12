package com.packtpub.e4.clock.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "com.packtpub.e4.clock.ui"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    private TrayItem trayItem;
    private Image image;

    /**
     * The constructor
     */
    public Activator() {
    }

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        final Display display = Display.getDefault();
        display.asyncExec(() -> {
            image = new Image(display, Activator.class.getResourceAsStream("/icons/sample.png"));
            final Tray tray = display.getSystemTray();
            if (tray != null && image != null) {
                trayItem = new TrayItem(tray, SWT.NONE);
                trayItem.setToolTipText("Hello World");
                trayItem.setVisible(true);
                trayItem.setText("Hello World");
                trayItem.setImage(image);
            }
        });
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

}
