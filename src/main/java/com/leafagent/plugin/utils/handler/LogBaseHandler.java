package com.leafagent.plugin.utils.handler;

import leafagent.info.BaseInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class LogBaseHandler implements LogHandler {
    protected final LinkedList<HandlerDataUpdateListener> updateListeners = new LinkedList<>();

    protected ArrayList<BaseInfo> logg;

    @Override
    public List<BaseInfo> getLog() {
        return logg;
    }

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

    @Override
    public void addHandlerDataUpdateListener(HandlerDataUpdateListener l) {
        updateListeners.add(l);
    }

    @Override
    public void update() {
        if (logg != null) {
            updateListeners.forEach((l) -> l.update(logg));
        }
    }
}
