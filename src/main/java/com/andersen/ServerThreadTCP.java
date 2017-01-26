package com.andersen;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

    class ServerThreadTCP implements Runnable {
    private final Socket socket;
    private final Logger logger = Logger.getLogger(ServerThreadTCP.class);
    private final ServerTCP serverTCP;

    ServerThreadTCP(Socket socket, ServerTCP serverTCP) {
        this.socket = socket;
        this.serverTCP = serverTCP;
    }

    public void run() {
        serverTCP.incrementClientOnServer();
        logger.info("Client's socket created.");
        logger.info("Client's on server increments.");
        logger.info("Clients on server " + serverTCP.getClientsOnServer());
        try {
            logger.info("New client thread started.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            String request = reader.readLine();
            writer.println(request + " from server.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            utilTCP.closeSocket(socket);
            serverTCP.decrementClientOnServer();
            logger.info("Client's on server decrements.");
        }
    }
}
