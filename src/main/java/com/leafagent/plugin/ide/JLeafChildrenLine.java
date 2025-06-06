package com.leafagent.plugin.ide;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * Paint leaf line to display a child elements of parent element.
 */
public class JLeafChildrenLine extends JPanel {
    private final int OFFSET_CHILDREN = 60;

    public JLeafChildrenLine() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(JBUI.Borders.emptyLeft(OFFSET_CHILDREN));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(JBColor.border());
        g.drawLine(
                getX() + OFFSET_CHILDREN / 2,
                0,
                getX() + OFFSET_CHILDREN / 2,
                getHeight());
    }
}