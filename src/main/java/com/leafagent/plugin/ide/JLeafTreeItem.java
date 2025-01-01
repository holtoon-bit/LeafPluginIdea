package com.leafagent.plugin.ide;

import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.Component;
import com.leafagent.view.LeafChildren;
import com.leafagent.view.LeafElement;
import leafagent.info.BaseInfo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class JLeafTreeItem extends JComponent {
    private LeafElement leafElement;
    private LeafChildren childrenList = new LeafChildren();

    private boolean isClicked = false;

    public JLeafTreeItem(BaseInfo info, List<BaseInfo> leafs) {
        leafElement = new LeafElement(info, leafs);

        leafElement.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isClicked) {
                    childrenList.add(new JLeafTree(null, leafs));
                    isClicked = true;
                } else {
                    childrenList.removeAll();
                    isClicked = false;
                }
                childrenList.updateUI();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        add(leafElement);
        add(childrenList);
    }
}