package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.leafagent.plugin.utils.handler.LogHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class JLeafTreeComponent {
    private final JPanel contentPanel = new JPanel();
    private JBScrollPane scrollPanel;
    private Dimension contentPanelSize;

    public JLeafTreeComponent(ToolWindow toolWindow, LogHandler logHandler) {
        logHandler.addDataUpdateListener((logg) -> initComponentPanel(toolWindow, logHandler));
        if (logHandler.getLog() != null) {
            initComponentPanel(toolWindow, logHandler);
        }

        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                contentPanelSize = contentPanel.getSize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }

    private void initComponentPanel(ToolWindow toolWindow, LogHandler logHandler) {
        scrollPanel = new JLeafScrollPane(toolWindow, logHandler);
        scrollPanel.setPreferredSize(contentPanelSize);
        contentPanel.add(scrollPanel, BorderLayout.PAGE_START);
        contentPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPanel.setPreferredSize(contentPanel.getSize());
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