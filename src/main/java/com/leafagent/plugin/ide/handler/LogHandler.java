package com.leafagent.plugin.ide.handler;

import leafagent.info.BaseInfo;

import java.util.List;

public interface LogHandler {
    List<BaseInfo> getLog();
    List<BaseInfo> getChildren(BaseInfo parent);
}
