package com.leafagent.plugin.ide;

import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.leafagent.plugin.utils.handler.LogHandler;
import leafagent.info.BaseInfo;

/**
 * Tree item with the {@link BaseInfo} info. Open children items by click.
 */
public class JLeafTreeItem extends JComponent {
    private final JLeafElement JLeafElement;
    private final JLeafChildrenLine childrenList = new JLeafChildrenLine();

    private boolean isClicked = false;

    public JLeafTreeItem(BaseInfo info, LogHandler handler) {
        JLeafElement = new JLeafElement(info);

        JLeafElement.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isClicked) {
                    for (BaseInfo parent : handler.getChildren(info)) {
                        childrenList.add(new JLeafTree(null, parent, handler));
                    }
                    isClicked = true;
                } else {
                    childrenList.removeAll();
                    isClicked = false;
                }
                childrenList.updateUI();
            }
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        add(JLeafElement);
        add(childrenList);
    }
}