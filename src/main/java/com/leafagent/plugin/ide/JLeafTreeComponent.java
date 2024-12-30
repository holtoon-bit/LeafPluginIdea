package com.leafagent.plugin.ide;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.json.psi.JsonFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiManager;
import com.intellij.ui.components.JBScrollPane;
import leafagent.info.BaseInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Objects;

public class JLeafTreeComponent {
    private final JPanel contentPanel = new JPanel();

    public JLeafTreeComponent(ToolWindow toolWindow) {
        JsonFile logFile = (JsonFile) PsiManager.getInstance(toolWindow.getProject()).findFile(Objects.requireNonNull(Objects.requireNonNull(toolWindow.getProject().getProjectFile()).getParent().getParent().findChild(".agent-leaf").findChild("logg.json")));
        Gson gson = new Gson();
        TypeToken<ArrayList<BaseInfo>> collectionType = new TypeToken<>(){};
        ArrayList<BaseInfo> logg = gson.fromJson(logFile.getText(), collectionType);

        JBScrollPane scroll = new JLeafScrollPane(toolWindow, logg);

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