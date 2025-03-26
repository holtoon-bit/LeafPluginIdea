package com.leafagent.plugin.ide;

import com.intellij.execution.Executor;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManager;
import com.intellij.execution.ui.RunContentWithExecutorListener;
import com.intellij.openapi.fileEditor.FileEditorManager;
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
import com.leafagent.plugin.utils.handler.LogFileHandler;
import com.leafagent.plugin.utils.AcceptLeafSocket;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

public class TreeToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Leaf for the Mobile debug
        project.getMessageBus().connect().subscribe(RunContentManager.TOPIC, new RunContentWithExecutorListener() {
            @Override
            public void contentSelected(@Nullable RunContentDescriptor descriptor, @NotNull Executor executor) {
                try {
                    AdbLeafSetting.forward();
                    AcceptLeafSocket socket = new AcceptLeafSocket();
                    socket.start();

                    JLeafTreeComponent debugToolWindowContent = new JLeafTreeComponent(toolWindow, socket.getHandler());
                    Content debugContent = ContentFactory.getInstance().createContent(debugToolWindowContent, "Debug", false);
                    toolWindow.getContentManager().addContent(debugContent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Leaf for opened file
        LogFileHandler handler = new LogFileHandler(project);

        project.getMessageBus().connect().subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
            @Override
            public void after(@NotNull List<? extends VFileEvent> events) {
                handler.setVirtualFile(FileEditorManager.getInstance(project).getSelectedTextEditor().getVirtualFile());
                handler.update();
            }
        });

        JLeafTreeComponent toolWindowContent = new JLeafTreeComponent(toolWindow, handler);
        Content content = ContentFactory.getInstance().createContent(toolWindowContent, "Current File", false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.getContentManager().getContent(toolWindowContent);
    }
}