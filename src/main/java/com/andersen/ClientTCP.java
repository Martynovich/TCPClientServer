package com.andersen;

import java.io.*;
import java.net.Socket;

public class ClientTCP {

	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			socket = new Socket("localhost", 3128);
			System.out.println("Enter message");
			String line = reader.readLine();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			writer.println(line);
			line = bReader.readLine();
			System.out.println(line);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
					socket = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
