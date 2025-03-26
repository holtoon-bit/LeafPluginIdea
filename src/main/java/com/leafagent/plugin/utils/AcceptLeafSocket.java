package com.leafagent.plugin.utils;

import com.leafagent.plugin.utils.handler.LogSocketHandler;
import com.leafagent.plugin.utils.handler.DataFresher;
import leafagent.utils.AdbLeafSetting;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public final class AcceptLeafSocket {
    private final String ADDRESS = "localhost";
    private final int UPDATE_TIMEOUT = 1000;

    private final LogSocketHandler handler;
    private final DataFresher<String> dataFresher;

    public AcceptLeafSocket() throws IOException {
        this.handler = new LogSocketHandler();
        this.dataFresher = new DataFresher<>();
    }

    public void start() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Socket socket = new Socket(ADDRESS, AdbLeafSetting.LOCAL_PORT);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    dataFresher.add(bufferedReader.readLine());
                    if (dataFresher.haveNext()) {
                        handler.setLog((String) dataFresher.getCurrent());
                        handler.update();
                    }
                    bufferedReader.close();
                    socket.close();
                    Thread.sleep(UPDATE_TIMEOUT);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    public LogSocketHandler getHandler() {
        return handler;
    }
}