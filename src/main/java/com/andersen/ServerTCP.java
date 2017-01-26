package com.andersen;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
* TCP server class.
*/
public class ServerTCP implements  Runnable {
    /**
     * Server's socket.
     */
    private ServerSocket serverSocket = null;
    /**
     * Pull of threads with clients.
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(15);
    /**
     * Number of clients on server.
     */
    private AtomicInteger clientsOnServer = new AtomicInteger();
    /**
     * Client's socket.
     */
    private Socket socket = null;
    /**
     * Sum of clients connected to server.
     */
    private int sumClients = 0;

    private final Logger logger = Logger.getLogger(ServerTCP.class);

    /**
     * Create TCP server on current host and port.
     * @param host Host
     * @param port Port
     */
    public ServerTCP(String host, int port) {
        logger.info("Server is started");
        try {
            serverSocket = new ServerSocket(port, 0, InetAddress.getByName(host));
        } catch (IOException e) {
            logger.error("Can't create server socket");
            e.printStackTrace();
            System.exit(0);
        }
        logger.info("Server socket is created.");
    }

    public void run() {
        try {
            while (true) {
                logger.info("Waiting for client.");
                try {
                    socket = serverSocket.accept();
                    logger.info("Sum of clients connected to server " + ++sumClients);
                    executor.execute(new ServerThreadTCP(socket, this));
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                } catch (IOException e) {
                    utilTCP.closeSocket(socket);
                    logger.info("Client's socked deleted.");
                    e.printStackTrace();
                }
            }
        } finally {
            utilTCP.closeSocket(serverSocket);
            logger.info("Server socked deleted.");
            logger.info("Server closed.");
        }
    }

    /**
     *
     * @return Number of clients on server.
     */
    int getClientsOnServer() {
        return clientsOnServer.get();
    }

    /**
     * Decrement number of clients on server.
     */
    void incrementClientOnServer() {
        clientsOnServer.incrementAndGet();
    }

    /**
     * Increment number of clients on server.
     */
    void decrementClientOnServer(){
        clientsOnServer.decrementAndGet();
    }
}
