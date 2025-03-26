package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.leafagent.plugin.utils.handler.LogHandler;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class JLeafTreeComponent extends JPanel {
    private JBScrollPane scrollPanel;
    private Dimension contentPanelSize;

    public JLeafTreeComponent(ToolWindow toolWindow, LogHandler logHandler) {
        logHandler.addHandlerDataUpdateListener((logg) -> {
            removeAll();
            initComponentPanel(toolWindow, logHandler);
            updateUI();
        });
        if (logHandler.getLog() != null) {
            initComponentPanel(toolWindow, logHandler);
        }

        setLayout(new BorderLayout(0, 20));
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                contentPanelSize = getSize();
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
        scrollPanel = new JLeafScrollPanel(toolWindow, logHandler);
        scrollPanel.setPreferredSize(contentPanelSize);
        add(scrollPanel, BorderLayout.PAGE_START);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPanel.setPreferredSize(getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }
}