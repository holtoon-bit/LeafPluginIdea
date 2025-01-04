package com.leafagent.view;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class JLeafChildren extends JPanel {
    private final int OFFSET_CHILDREN = 60;

    public JLeafChildren() {
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