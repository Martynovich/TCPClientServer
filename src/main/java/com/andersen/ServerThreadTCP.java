package com.andersen;

import java.io.*;
import java.net.Socket;

class ServerThreadTCP extends Thread {
    private Socket socket = null;
    private BufferedReader reader = null;
    private PrintWriter writer = null;


    void serverThread(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    @Override
    public void run() {
        try {
            String request = reader.readLine();
            writer.println(request + " from server");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ServerTCPUtil.closeSocket(socket);
        }
    }
}
