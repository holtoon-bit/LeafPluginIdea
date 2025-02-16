package com.leafagent.plugin.ide;

import com.intellij.ui.JBColor;
import leafagent.info.BaseInfo;

import javax.swing.*;
import java.awt.*;

public class JLeafElement extends JPanel {
    private final int FONT_SIZE = 14;

    public JLeafElement(BaseInfo info) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor.border(), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));

        JLabel label1 = new LeafElementLabel(info.getName());
        JLabel label2 = new LeafElementLabel(info.getDesc(), true);
        JLabel label3 = new LeafElementLabel(Long.toString(info.getStartMillis()));
        JLabel label4 = new LeafElementLabel(" +" + (info.getEndMillis() - info.getStartMillis()), true);
        JPanel group = new JPanel();
        group.setLayout(new BoxLayout(group, BoxLayout.X_AXIS));
        group.setAlignmentX(LEFT_ALIGNMENT);
        JLabel label5 = new LeafElementLabel(Long.toString(info.getEndMillis()));

        add(label1);
        add(label2);
        group.add(label3);
        group.add(label4);
        add(group);
        add(label5);
    }

    private class LeafElementLabel extends JLabel {
        public LeafElementLabel(String text) {
            super(text);
            setFont(new Font(getFont().getName(), getFont().getStyle(), FONT_SIZE));
        }

        public LeafElementLabel(String text, boolean isSupporting) {
            this(text);
            if (isSupporting) {
                setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), 100));
            }
        }
    }
}