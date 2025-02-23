package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import java.awt.Component;

import com.intellij.util.ui.JBUI;
import com.leafagent.plugin.ide.handler.LeafLogFileHandler;
import com.leafagent.plugin.ide.handler.LogHandler;
import leafagent.info.BaseInfo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class JLeafScrollPane extends JBScrollPane {
    public JLeafScrollPane(ToolWindow toolWindow, LogHandler handler) {
//        super(new JLeafTree(toolWindow, new BaseInfo.Build().setName("root").setId(-1).build(), handler));
        super(new RootChildren(toolWindow, handler));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private static class RootChildren extends JPanel {
        public RootChildren(ToolWindow toolWindow, LogHandler handler) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setAlignmentX(Component.LEFT_ALIGNMENT);
            for (BaseInfo parent : handler.getChildren(new BaseInfo.Build().setName("root").setId(-1).build())) {
                add(new JLeafTree(toolWindow, parent, handler));
            }
        }
    }
}
