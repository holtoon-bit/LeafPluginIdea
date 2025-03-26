package com.leafagent.plugin.utils.handler;

public class DataFresher<T> {
    private T currentObject = null;
    private boolean isFresh = true;

    public void add(T newValue) {
        if (newValue != null && (currentObject == null || !currentObject.equals(newValue))) {
            currentObject = newValue;
            isFresh = true;
        }
    }

    public Object getCurrent() {
        if (!isFresh) {
            return null;
        }
        isFresh = false;
        return currentObject;
    }

    public boolean haveNext() {
        return isFresh;
    }
}
