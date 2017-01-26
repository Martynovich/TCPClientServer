package com.andersen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TCP client launcher.
 */
public class StartTCPClient {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter message");
        String request = reader.readLine();
        String response = new ClientTCP("localhost", 3128).sendAndReceive(request);
        System.out.println("\n" + response);
    }
}
