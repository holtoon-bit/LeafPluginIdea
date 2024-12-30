package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import leafagent.info.BaseInfo;

import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.*;
import java.util.List;

public class JLeafTree extends JComponent {
    public JLeafTree(ToolWindow toolWindow, List<BaseInfo> leafs) {
        if (toolWindow != null) {
            setSize(toolWindow.getContentManager().getComponent().getParent().getSize());
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        for (BaseInfo leaf : leafs) {
            add(new JLeafTreeItem(leaf.getName(), List.of(new BaseInfo.Build().setName("get").build(), new BaseInfo.Build().setName("add").build())));
        }
        updateUI();
    }
}