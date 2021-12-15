package com.packtpub.e4.clock.ui.views;

import java.time.ZoneId;
import java.util.Map;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

public class TimeZoneLabelProvider extends LabelProvider {
    private final ISharedImages images;
    private final ImageRegistry ir;

    public TimeZoneLabelProvider(final ISharedImages images, final ImageRegistry ir) {
        this.images = images;
        this.ir = ir;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public String getText(final Object element) {
        if (element instanceof Map) {
            return "Time Zones";
        } else if (element instanceof Map.Entry) {
            return ((Map.Entry) element).getKey().toString();
        } else if (element instanceof ZoneId) {
            return ((ZoneId) element).getId().split("/")[1];
        } else {
            return "Unknown type: " + element.getClass();
        }
    }

    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof Object[]) {
            return (Object[]) inputElement;
        } else {
            return new Object[0];
        }
    }

    @Override
    public Image getImage(final Object element) {
        if (element instanceof Map.Entry) {
            return images.getImage(ISharedImages.IMG_OBJ_FOLDER);
        } else if (element instanceof ZoneId) {
            return ir.get("sample");
        } else {
            return super.getImage(element);
        }
    }
}
