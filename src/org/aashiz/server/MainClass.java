package org.aashiz.server;

public class MainClass {

	
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Cannot start!!");
			return;
		}
		Server  ser = new  Server(Integer.parseInt(args[0]));
		ser.start();
	}
}
