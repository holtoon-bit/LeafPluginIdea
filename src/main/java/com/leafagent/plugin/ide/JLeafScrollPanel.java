package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;

import com.leafagent.plugin.utils.handler.LogHandler;
import leafagent.info.BaseInfo;


public class JLeafScrollPanel extends JBScrollPane {
    public JLeafScrollPanel(ToolWindow toolWindow, LogHandler handler) {
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
