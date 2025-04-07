package com.leafagent.plugin.utils;

import com.leafagent.plugin.utils.handler.LogJSONHandler;
import com.leafagent.plugin.utils.handler.DataFresher;
import leafagent.utils.AdbLeafSetting;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Socket receiving the Leaf Log is added in the {@link DataFresher}
 * for timely handler update (see {@link com.leafagent.plugin.utils.handler.LogHandler LogHandler}).
 */
public final class AcceptLeafSocket {
    private final String ADDRESS = "localhost";
    private final int UPDATE_TIMEOUT = 1000;

    private final LogJSONHandler handler;
    private final DataFresher<String> dataFresher;

    public AcceptLeafSocket() throws IOException {
        this.handler = new LogJSONHandler();
        this.dataFresher = new DataFresher<>();
    }

    /**
     * Start a socket.
     * It gets new value of the Leaf Log and add to the {@link DataFresher} all the time.<br>
     * And call all the {@link com.leafagent.plugin.utils.handler.HandlerDataUpdateListener HandlerDataUpdateListener}.
     */
    public void start() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Socket socket = new Socket(ADDRESS, AdbLeafSetting.LOCAL_PORT);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    dataFresher.add(bufferedReader.readLine());
                    if (dataFresher.haveNext()) {
                        handler.setLog(dataFresher.getCurrent());
                        handler.update();
                    }
                    bufferedReader.close();
                    socket.close();
                    Thread.sleep(UPDATE_TIMEOUT);
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
            }
        });
        thread.start();
    }

    public LogJSONHandler getHandler() {
        return handler;
    }
}