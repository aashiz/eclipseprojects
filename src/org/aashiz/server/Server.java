package org.aashiz.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static t.log.*;


public class Server {
	private boolean CONTINUE = true;
	private ServerSocket sock = null;
	private int port = 5555 ;
	public Server(int PORT){
		port = PORT ;
	}
	
	public Server(){
		
	}
	
	public void start(){
		try {
			sock = new  ServerSocket(port) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log("Couldn't start a server at " + port);
			e.printStackTrace();
		}
		log("Starting new server at "+ port);
		
		while(CONTINUE){
			try {
				Socket socke = sock.accept();
				Clients ss = new Clients(socke);
				ss.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log("Couldn't accept the client!");
				CONTINUE = false;
				e.printStackTrace();
			}
		}
	}
}
