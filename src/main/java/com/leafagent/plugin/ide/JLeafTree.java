package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.leafagent.view.LeafElement;
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

        int prevLeafIndex = leafs.size();
        for (int i = leafs.size()-1; i >= 0; i--) {
            if (leafs.get(i).getParentId() == leafs.get(0).getParentId()) {
                add(new JLeafTreeItem(leafs.get(i), leafs.subList(i+1, prevLeafIndex)), 0);
                prevLeafIndex = i;
            }
        }
        updateUI();
    }
}