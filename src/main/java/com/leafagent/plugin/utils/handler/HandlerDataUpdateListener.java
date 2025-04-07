package com.leafagent.plugin.utils.handler;

import leafagent.info.BaseInfo;

import java.util.ArrayList;

/**
 * Listener of data update in the {@link LogHandler}.
 */
public interface HandlerDataUpdateListener {
    void update(ArrayList<BaseInfo> logg);
}
