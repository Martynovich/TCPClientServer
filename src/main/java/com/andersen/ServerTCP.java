package com.andersen;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ServerTCP{

    void startTCPServer(){
        String ipAddress = "localhost";
        int port = 3128;
        System.out.println("Server is started");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 0, InetAddress.getByName(ipAddress));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = null;

        try {
            while (true) {
                try {
                    socket = serverSocket.accept();
                    new ServerThreadTCP().serverThread(socket);
                } catch (IOException e) {
                    ServerTCPUtil.closeSocket(socket);
                    e.printStackTrace();
                }
            }
        } finally {
            ServerTCPUtil.closeSocket(serverSocket);
        }
    }
}
