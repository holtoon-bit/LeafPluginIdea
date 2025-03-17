package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import java.awt.Component;

import com.leafagent.plugin.utils.handler.LogHandler;
import leafagent.info.BaseInfo;

import javax.swing.*;

public class JLeafScrollPane extends JBScrollPane {
    public JLeafScrollPane(ToolWindow toolWindow, LogHandler handler) {
        super(new RootChildren(toolWindow, handler));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private static class RootChildren extends JPanel {
        public RootChildren(ToolWindow toolWindow, LogHandler handler) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setAlignmentX(Component.LEFT_ALIGNMENT);
            for (BaseInfo parent : handler.getChildren(new BaseInfo.Build().setName("root").setClassName("main").setThreadName("main").setId(-1).build())) {
                add(new JLeafTree(toolWindow, parent, handler));
            }
        }
    }
}
