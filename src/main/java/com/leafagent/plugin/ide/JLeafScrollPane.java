package com.leafagent.plugin.ide;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JLeafScrollPane extends JBScrollPane {
    public JLeafScrollPane(ToolWindow toolWindow, List leafs) {
        super(new JLeafTree(toolWindow, leafs));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
