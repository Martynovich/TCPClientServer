package com.andersen;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
			System.out.println("Server is started");
			Socket serverSocket = server.accept();
			BufferedReader reader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream())), true);
			String request = reader.readLine();
			writer.println(request + " from server");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
					server = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
