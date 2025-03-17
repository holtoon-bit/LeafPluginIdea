package com.leafagent.plugin.ide;

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import leafagent.utils.AdbLeafSetting;
import org.jetbrains.annotations.NotNull;
import com.leafagent.plugin.utils.handler.LeafLogFileHandler;
import com.leafagent.plugin.utils.AcceptLeafSocket;
import java.io.IOException;
import java.util.List;

public class TreeToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        LeafLogFileHandler handler = new LeafLogFileHandler(project);

//        try {
//            AdbLeafSetting.forward();
//            AcceptLeafSocket socket = new AcceptLeafSocket(handler);
//            socket.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        project.getMessageBus().connect().subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
            @Override
            public void after(@NotNull List<? extends VFileEvent> events) {
                handler.setVirtualFile(FileEditorManager.getInstance(project).getSelectedTextEditor().getVirtualFile());
            }
        });

        JLeafTreeComponent toolWindowContent = new JLeafTreeComponent(toolWindow, handler);
        Content content = ContentFactory.getInstance().createContent(toolWindowContent.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}