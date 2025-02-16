package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import java.awt.Component;
import com.leafagent.plugin.ide.handler.LogHandler;
import leafagent.info.BaseInfo;

public class JLeafScrollPane extends JBScrollPane {
    public JLeafScrollPane(ToolWindow toolWindow, LogHandler handler) {
        super(new JLeafTree(toolWindow, new BaseInfo.Build().setName("root").setId(-1).build(), handler));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
