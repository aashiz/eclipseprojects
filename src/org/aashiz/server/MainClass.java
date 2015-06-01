package org.aashiz.server;

public class MainClass {

	
	public static void main(String[] args){
		Server  ser = new  Server(5555);
		ser.start();
	}
}
