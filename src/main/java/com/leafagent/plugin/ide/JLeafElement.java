package com.leafagent.plugin.ide;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import leafagent.info.BaseInfo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JLeafElement extends JPanel {
    public JLeafElement(BaseInfo info) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor.border(), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));

        // Method and class names
        JLabel name = new JLeafLabel(info.getName());
        JLabel className = new JLeafDescriptionLabel(info.getClassName());
        if (info.getClassName() != null) {
            className = new JLeafDescriptionLabel(info.getClassName().substring(info.getClassName().lastIndexOf("/")+1));
        }
        className.setBorder(new CompoundBorder(className.getBorder(), JBUI.Borders.emptyLeft(8)));
        JPanel nameGroup = new JPanel();
        nameGroup.setLayout(new BoxLayout(nameGroup, BoxLayout.X_AXIS));
        nameGroup.setAlignmentX(LEFT_ALIGNMENT);
        // Description
        JLabel desc = new JLeafDescriptionLabel(info.getDesc());
        // Work time
        JLabel startTime = new JLeafLabel(
                new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(info.getStartMillis())));
        JLabel endTime = new JLeafLabel("in process...");
        JLabel lengthTime = new JLeafDescriptionLabel("");
        if (info.getEndMillis() != 0) {
            endTime = new JLeafLabel(
                    new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(info.getEndMillis())));
            lengthTime = new JLeafDescriptionLabel("+" + (info.getEndMillis() - info.getStartMillis()));
            lengthTime.setBorder(new CompoundBorder(lengthTime.getBorder(), JBUI.Borders.emptyLeft(4)));
        }
        JPanel lengthTimeGroup = new JPanel();
        lengthTimeGroup.setLayout(new BoxLayout(lengthTimeGroup, BoxLayout.X_AXIS));
        lengthTimeGroup.setAlignmentX(LEFT_ALIGNMENT);

        nameGroup.add(name);
        nameGroup.add(className);
        add(nameGroup);
        add(desc);
        lengthTimeGroup.add(startTime);
        lengthTimeGroup.add(lengthTime);
        add(lengthTimeGroup);
        add(endTime);
    }

    private class JLeafLabel extends JLabel {
        public JLeafLabel(String text) {
            super(text);
            setFont(new Font(getFont().getName(), getFont().getStyle(), 14));
            setBorder(new CompoundBorder(getBorder(), JBUI.Borders.emptyBottom(1)));
        }
    }

    private class JLeafDescriptionLabel extends JLeafLabel {
        public JLeafDescriptionLabel(String text) {
            super(text);
            setForeground(new JBColor(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), 140),
                    new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), 140)));
        }
    }
}