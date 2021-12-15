package com.packtpub.e4.clock.ui.views;

import java.util.Collection;
import java.util.Map;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TimeZoneContentProvider implements ITreeContentProvider {
    @Override
    @SuppressWarnings("rawtypes")
    public boolean hasChildren(final Object element) {
        if (element instanceof Map) {
            return !((Map) element).isEmpty();
        } else if (element instanceof Map.Entry) {
            return hasChildren(((Map.Entry) element).getValue());
        } else if (element instanceof Collection) {
            return !((Collection) element).isEmpty();
        } else {
            return false;
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof Map) {
            return ((Map) parentElement).entrySet().toArray();
        } else if (parentElement instanceof Map.Entry) {
            return getChildren(((Map.Entry) parentElement).getValue());
        } else if (parentElement instanceof Collection) {
            return ((Collection) parentElement).toArray();
        } else {
            return new Object[0];
        }
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof Object[]) {
            return (Object[]) inputElement;
        } else {
            return new Object[0];
        }
    }

    @Override
    public Object getParent(final Object element) {
        return null;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }
}