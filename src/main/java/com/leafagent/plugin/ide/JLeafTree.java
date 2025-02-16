package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.leafagent.plugin.ide.handler.LogHandler;
import leafagent.info.BaseInfo;

import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.Component;

public class JLeafTree extends JComponent {
    public JLeafTree(ToolWindow toolWindow, BaseInfo info, LogHandler handler) {
        if (toolWindow != null) {
            setSize(toolWindow.getContentManager().getComponent().getParent().getSize());
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        add(new JLeafTreeItem(info, handler));
        updateUI();
    }
}