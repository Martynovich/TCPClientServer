package com.andersen;

import java.io.Closeable;
import java.io.IOException;

class ServerTCPUtil {
    static void closeSocket(Closeable socket) {
        try {
            socket.close();
            if (socket != null) {
                socket = null;
            }
        } catch (IOException e1) {
            System.out.println("Socket not closed");
        }
    }
}
