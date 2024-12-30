package com.leafagent.plugin.ide;

import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.Component;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.JBUI;
import leafagent.info.BaseInfo;

import java.util.List;

public class JLeafTreeItem extends JComponent {
    public JLeafTreeItem(String name, List<BaseInfo> leafs) {
        JBPanel childrenList = new JBPanel();
        childrenList.setLayout(new BoxLayout(childrenList, BoxLayout.Y_AXIS));
        childrenList.setBorder(JBUI.Borders.emptyLeft(10));

        JBCheckBox openButton = new JBCheckBox(name);
        openButton.addItemListener((itemEvent) -> {
            if (((JBCheckBox) itemEvent.getItem()).isSelected()) {
                childrenList.add(new JLeafTree(null, leafs));
            } else {
                childrenList.removeAll();
            }
            childrenList.updateUI();
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        add(openButton);
        add(childrenList);
    }
}
