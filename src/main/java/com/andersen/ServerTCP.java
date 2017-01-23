package com.andersen;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
	
	public static void main(String[] args) {
		try{
			ServerSocket server = new ServerSocket(3128, 0 ,
					InetAddress.getByName("localhost"));
			System.out.println("Server is started");
			Socket serverSocket = server.accept();
			InputStream is = serverSocket.getInputStream();
			OutputStream os = serverSocket.getOutputStream();
			byte buf[] = new byte[64*1024];
			int r = is.read(buf);
			String request = new String(buf, 0, r);
			String response = request + " servers answer";
			os.write(response.getBytes());
			serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
