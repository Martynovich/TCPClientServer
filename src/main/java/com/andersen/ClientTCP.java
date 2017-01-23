package com.andersen;

import java.net.Socket;

public class ClientTCP {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 3128);
			String request = "Hello";
			socket.getOutputStream().write(request.getBytes());
			byte buf[] = new byte[64*1024];
			int r = socket.getInputStream().read(buf);
			String response = new String(buf, 0, r);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
