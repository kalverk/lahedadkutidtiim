package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class Result extends Thread{
	private BufferedReader input;
	

	Result(BufferedReader in){
		this.input = in;
	}
	
	public void run(){
		String inputString;
		while(true){
			try {
				if ((inputString = input.readLine()) != null) {
					System.out.println(inputString);
				}
			} catch (SocketException e) {
				System.out.println("Connection lost.");
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

