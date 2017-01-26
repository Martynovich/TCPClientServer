package com.andersen;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * TCP Client class for connection with TCP server
 */
public class ClientTCP {

    /**
     * Response from server.
     */
    private String response = null;
    /**
     * Client's socket
     */
    private Socket socket = null;

    private final static Logger logger = Logger.getLogger(ClientTCP.class);

    /**
     * Create client.
     * @param host Host
     * @param port Port
     */
    public ClientTCP(String host, int port) {
        try {
            socket = new Socket(host, port);
            logger.info("Client's socked created.");
        } catch (IOException e) {
            logger.info("Server port is busy now.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Send request to server snd return response from server.
     * @param request Request to server.
     * @return Response from server.
     */
    public String sendAndReceive(String request) {
        try {
            logger.info("New client thread started.");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            writer.println(request);
            response = bReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utilTCP.closeSocket(socket);
            logger.info("Client closed.");
        }
        return response;
    }
}
