package com.andersen;

import java.io.IOException;

/**
 * TCP server launcher.
 */
public class StartTCPServer {

    public static void main(String[] args) throws IOException {
        new Thread(new ServerTCP("localhost", 3128)).start();
    }
}
