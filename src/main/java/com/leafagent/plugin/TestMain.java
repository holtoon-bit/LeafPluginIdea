package com.leafagent.plugin;

import com.leafagent.plugin.utils.AcceptLeafSocket;
import com.leafagent.plugin.utils.handler.LogSocketHandler;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        try {
            AcceptLeafSocket socket = new AcceptLeafSocket();
            socket.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
