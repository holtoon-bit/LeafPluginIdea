package com.leafagent.plugin.ide;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class LeafNotification {
    public static void notifyError(Project project, String title, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("Leaf Notification")
                .createNotification(title, content, NotificationType.ERROR)
                .notify(project);
    }
}
