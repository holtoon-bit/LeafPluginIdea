package com.leafagent.plugin.utils.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import leafagent.info.BaseInfo;
import java.util.ArrayList;

public class LogSocketHandler extends LogBaseHandler {
    private final Gson gson;
    private final TypeToken<ArrayList<BaseInfo>> collectionType;

    public LogSocketHandler() {
        this.gson = new Gson();
        this.collectionType = new TypeToken<>(){};
    }

    @Override
    public void setLog(String log) {
        logg = gson.fromJson(log, collectionType);
    }
}
