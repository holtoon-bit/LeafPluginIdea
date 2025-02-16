package com.leafagent.plugin.ide.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.project.Project;
import leafagent.info.BaseInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LeafLogFileHandler implements LogHandler {
    private Project project;

    private ArrayList<BaseInfo> logg;

    public LeafLogFileHandler(@NotNull Project project) {
        this.project = project;
        handleLog();
    }

    private void handleLog() {
        String logValue = PsiManager.getInstance(project).findFile(
                Objects.requireNonNull(
                        Objects.requireNonNull(project.getProjectFile())
                                .getParent().getParent().findChild(".agent-leaf")
                                .findChild("logg.json")
                )
        ).getText();
        Gson gson = new Gson();
        TypeToken<ArrayList<BaseInfo>> collectionType = new TypeToken<>(){};
        logg = gson.fromJson(logValue, collectionType);
    }

    @Override
    public List<BaseInfo> getLog() {
        return logg;
    }

    @Override
    public List<BaseInfo> getChildren(BaseInfo parent) {
        List<BaseInfo> children = new LinkedList<>();
        for (int i = logg.indexOf(parent)+1; i < logg.size(); i++) {
            if (logg.get(i).getParentId() == parent.getId()) {
                children.add(logg.get(i));
            }
        }
        return children;
    }
}
