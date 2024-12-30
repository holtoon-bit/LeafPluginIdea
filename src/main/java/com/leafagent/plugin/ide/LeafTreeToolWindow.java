package com.leafagent.plugin.ide;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.json.psi.JsonFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.PsiManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import leafagent.info.BaseInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;


public class LeafTreeToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        JsonFile logFile = (JsonFile) PsiManager.getInstance(project).findFile(Objects.requireNonNull(Objects.requireNonNull(project.getProjectFile()).getParent().getParent().findChild(".agent-leaf").findChild("logg.json")));
//        Gson gson = new Gson();
//        TypeToken<ArrayList<BaseInfo>> collectionType = new TypeToken<>(){};
//        ArrayList<BaseInfo> logg = gson.fromJson(logFile.getText(), collectionType);
        JLeafTreeComponent toolWindowContent = new JLeafTreeComponent(toolWindow);
        Content content = ContentFactory.getInstance().createContent(toolWindowContent.getContentPanel(), "", true);
        toolWindow.getContentManager().addContent(content);
    }
}