package com.leafagent.plugin.ide;

import com.intellij.execution.Executor;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManager;
import com.intellij.execution.ui.RunContentWithExecutorListener;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
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

/**
 * Implementation the {@link ToolWindowFactory} creating the {@link ToolWindow}
 * to display two tabs with the {@link JLeafTreeComponent}.<br>
 * This tabs displays the {@code Current File} (last opened file) and {@code Debug} (observe of runtime Android app).
 */
public class TreeToolWindow implements ToolWindowFactory {
    private static final String LEAF_FILE_EXTENSION_NAME = "json";
    private static final String LEAF_DEBUG_WINDOW_NAME = "Debug";
    private static final String CURRENT_FILE_WINDOW_NAME = "Current File";

    private JLeafTreeComponent debugToolWindowContent;
    private Content debugContent;

    /**
     * Create the {@code Current File} or {@code Debug} tabs, when start observing.
     *
     * @param project {@link Project}
     * @param toolWindow {@link ToolWindow}
     */
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Leaf for the Mobile debug
        project.getMessageBus().connect().subscribe(RunContentManager.TOPIC, new RunContentWithExecutorListener() {
            @Override
            public void contentSelected(@Nullable RunContentDescriptor descriptor, @NotNull Executor executor) {
                try {
                    if (descriptor != null && descriptor.getExecutionId() == 2) {
                        AdbLeafSetting.forward();
                        AcceptLeafSocket socket = new AcceptLeafSocket();
                        socket.start();

                        debugToolWindowContent = new JLeafTreeComponent(toolWindow, socket.getHandler());
                        debugContent = ContentFactory.getInstance().createContent(debugToolWindowContent, LEAF_DEBUG_WINDOW_NAME, false);
                        toolWindow.getContentManager().addContent(debugContent);
                        toolWindow.getContentManager().setSelectedContent(debugContent);
                    }
                } catch (IOException e) {
                    LeafNotification.notifyError(project, "Cannot connect to the Android app", "Try connecting your mobile device or launch the emulator to configure adb-connect");
                }
            }
        });

        // Leaf for opened file
        LogFileHandler handler = new LogFileHandler(project);

        project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                if (event.getManager().getSelectedTextEditor() != null) {
                    VirtualFile file = event.getManager().getSelectedTextEditor().getVirtualFile();
                    if (file.getExtension().equals(LEAF_FILE_EXTENSION_NAME)) {
                        handler.setVirtualFile(file);
                        handler.update();
                    }
                }
            }
        });

        JLeafTreeComponent toolWindowContent = new JLeafTreeComponent(toolWindow, handler);
        Content content = ContentFactory.getInstance().createContent(toolWindowContent, CURRENT_FILE_WINDOW_NAME, false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.getContentManager().getContent(toolWindowContent);
    }
}