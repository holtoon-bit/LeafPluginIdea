package com.leafagent.plugin.utils.handler;

import leafagent.info.BaseInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Handler for the Leaf Log consisting of the {@link BaseInfo}, storing data in {@link ArrayList}.
 */
public abstract class LogBaseHandler implements LogHandler {
    protected final LinkedList<HandlerDataUpdateListener> updateListeners = new LinkedList<>();

    protected ArrayList<BaseInfo> logg;

    @Override
    public List<BaseInfo> getLog() {
        return logg;
    }

    /**
     * Get children by {@code id} of the {@link BaseInfo} parent.
     *
     * @param parent {@link BaseInfo}
     * @return {@link List} with {@link BaseInfo} children.
     */
    @Override
    public List<BaseInfo> getChildren(BaseInfo parent) {
        List<BaseInfo> children = new LinkedList<>();
        for (int i = logg.indexOf(parent)+1; i < logg.size(); i++) {
            if (logg.get(i).getParentId() == parent.getId()) {
                children.add(logg.get(i));
            }
        }
        return children;
    }

    /**
     * Add a listener of the Leaf Log update in object of this class.
     *
     * @param l {@link HandlerDataUpdateListener}
     */
    @Override
    public void addHandlerDataUpdateListener(HandlerDataUpdateListener l) {
        updateListeners.add(l);
    }

    /**
     * Call all the added the {@link HandlerDataUpdateListener}.
     */
    @Override
    public void update() {
        if (logg != null) {
            updateListeners.forEach((l) -> l.update(logg));
        }
    }
}
