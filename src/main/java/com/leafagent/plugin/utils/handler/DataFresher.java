package com.leafagent.plugin.utils.handler;

/**
 * Receivers a data and determines whether it was before. If true, then the "fresh" data is null, otherwise it value.
 *
 * @param <T> data type.
 */
public class DataFresher<T> {
    private T currentObject = null;
    private boolean isFresh = true;

    /**
     * Determines that the same value was not received last time and update the {@code currentObject} value.
     *
     * @param newValue new value.
     */
    public void add(T newValue) {
        if (newValue != null && (currentObject == null || !currentObject.equals(newValue))) {
            currentObject = newValue;
            isFresh = true;
        }
    }

    /**
     * @return New value or null (see {@link #add(Object) add()}).
     */
    public T getCurrent() {
        if (!isFresh) {
            return null;
        }
        isFresh = false;
        return currentObject;
    }

    /**
     * Check object of this class have a "fresh" value.
     * @return {@link Boolean boolean}
     */
    public boolean haveNext() {
        return isFresh;
    }
}
