package com.leafagent.plugin.utils;

import java.io.*;
import java.net.Socket;


public final class AcceptLeafSocket extends Socket {
    private static final String ADDRESS = "localhost";
    private static final int HOST = 2112;

    public AcceptLeafSocket() throws IOException {
        // обработать ошибку в случае если телефон отключен
        super(ADDRESS, HOST);
    }

    public void start() {
        Thread thread = new Thread(new AcceptLeafRunnable());
        thread.start();
    }

    private class AcceptLeafRunnable implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(getInputStream()));
                while (!isInputShutdown()) {
                    String allInfoInJson = input.readLine();
                    System.out.println(allInfoInJson);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}