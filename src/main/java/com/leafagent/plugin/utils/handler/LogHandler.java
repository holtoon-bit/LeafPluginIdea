package com.leafagent.plugin.utils.handler;

import com.intellij.openapi.vfs.VirtualFile;
import leafagent.info.BaseInfo;

import java.util.List;

public interface LogHandler {
    void setVirtualFile(VirtualFile vf);
    List<BaseInfo> getLog();
    List<BaseInfo> getChildren(BaseInfo parent);
    void setLog(String log);
    void addDataUpdateListener(DataUpdateListener l);
}
