package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.leafagent.plugin.ide.handler.LogHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class JLeafTreeComponent {
    private final JPanel contentPanel = new JPanel();

    public JLeafTreeComponent(ToolWindow toolWindow, LogHandler logHandler) {
        JBScrollPane scroll = new JLeafScrollPane(toolWindow, logHandler);

        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.add(scroll, BorderLayout.PAGE_START);
        contentPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                scroll.setPreferredSize(contentPanel.getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}