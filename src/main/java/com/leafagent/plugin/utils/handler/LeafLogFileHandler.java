package com.leafagent.plugin.utils.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.project.Project;
import leafagent.info.BaseInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LeafLogFileHandler implements LogHandler {
    private final Project project;
    private final LinkedList<DataUpdateListener> updateListeners = new LinkedList<>();

    private final Gson gson;
    private final TypeToken<ArrayList<BaseInfo>> collectionType;
    private ArrayList<BaseInfo> logg;

    public LeafLogFileHandler(@NotNull Project project) {
        this.project = project;
        this.gson = new Gson();
        this.collectionType = new TypeToken<>(){};
    }

    @Override
    public void setVirtualFile(VirtualFile vf) {
        String logValue = Objects.requireNonNull(PsiManager.getInstance(project).findFile(vf)).getText();
        setLog(logValue);
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

    @Override
    public void setLog(String log) {
        logg = gson.fromJson(log, collectionType);
        callUpdateListeners();
    }

    @Override
    public void addDataUpdateListener(DataUpdateListener l) {
        updateListeners.add(l);
    }

    private void callUpdateListeners() {
        updateListeners.forEach((l) -> l.update(logg));
    }
}
