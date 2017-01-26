package com.andersen;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Util class for server and client.
 */
class utilTCP {

    private final static Logger logger = Logger.getLogger(utilTCP.class.getName());

    static void closeSocket(Closeable socket) {
        try {
            socket.close();
            if (socket != null) {
                socket = null;
            }
        } catch (IOException e1) {
            logger.info("Socket not closed");
        }
    }
}
