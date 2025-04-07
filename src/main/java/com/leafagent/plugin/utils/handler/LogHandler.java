package com.leafagent.plugin.utils.handler;

import leafagent.info.BaseInfo;

import java.util.List;

/**
 * Interface of handler for the Leaf Log.
 */
public interface LogHandler {
    List<BaseInfo> getLog();
    List<BaseInfo> getChildren(BaseInfo parent);
    void setLog(String log);
    void addHandlerDataUpdateListener(HandlerDataUpdateListener l);
    void update();
}
