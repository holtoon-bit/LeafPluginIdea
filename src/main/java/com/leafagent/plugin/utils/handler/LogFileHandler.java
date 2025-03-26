package com.leafagent.plugin.utils.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.project.Project;
import leafagent.info.BaseInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class LogFileHandler extends LogBaseHandler {
    private final Project project;

    private final Gson gson;
    private final TypeToken<ArrayList<BaseInfo>> collectionType;

    public LogFileHandler(@NotNull Project project) {
        this.project = project;
        this.gson = new Gson();
        this.collectionType = new TypeToken<>(){};
    }

    public void setVirtualFile(VirtualFile vf) {
        setLog(Objects.requireNonNull(PsiManager.getInstance(project).findFile(vf)).getText());
    }

    @Override
    public void setLog(String log) {
        logg = gson.fromJson(log, collectionType);
    }
}
