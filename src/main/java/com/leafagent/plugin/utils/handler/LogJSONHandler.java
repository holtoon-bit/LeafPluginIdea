package com.leafagent.plugin.utils.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import leafagent.info.BaseInfo;
import java.util.ArrayList;

/**
 * Extension {@link LogBaseHandler} receiving the Leaf Log from {@link String} in {@code JSON} format.
 */
public class LogJSONHandler extends LogBaseHandler {
    private final Gson gson;
    private final TypeToken<ArrayList<BaseInfo>> collectionType;

    public LogJSONHandler() {
        this.gson = new Gson();
        this.collectionType = new TypeToken<>(){};
    }

    @Override
    public void setLog(String log) {
        logg = gson.fromJson(log, collectionType);
    }
}
