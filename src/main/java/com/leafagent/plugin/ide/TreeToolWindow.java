package com.leafagent.plugin.ide;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.leafagent.plugin.ide.handler.LeafLogFileHandler;
import com.leafagent.plugin.utils.AcceptLeafSocket;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TreeToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        try {
//            AcceptLeafSocket server = new AcceptLeafSocket();
//            server.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        JLeafTreeComponent toolWindowContent = new JLeafTreeComponent(toolWindow, new LeafLogFileHandler(project));
        Content content = ContentFactory.getInstance().createContent(toolWindowContent.getContentPanel(), "", true);
        toolWindow.getContentManager().addContent(content);
    }
}