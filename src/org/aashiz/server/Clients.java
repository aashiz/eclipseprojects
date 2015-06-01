package org.aashiz.server;
import java.net.*;
import java.io.*;

import sun.nio.cs.UnicodeEncoder;
import sun.text.normalizer.UnicodeSet;
import t.Utilities;

import com.sun.xml.internal.fastinfoset.Encoder;

import static t.log.*;
public class Clients extends Thread  {
	final int USERIDBYTES = 20;
	final String ERROR = "404 ERROR OCCURED \n";
	final String OK = "200 REQUEST OK \n";
	private Socket ClientSocket;
	private String UserID ;
	private boolean isJAVA;
	private boolean Success ;
	private int UserHash;
	public Clients(Socket clientSocket){
		System.out.println("A client connected " + clientSocket.getRemoteSocketAddress());
		this.ClientSocket = clientSocket;
		
		
		}
	
	private boolean getUserID(){
		
		InputStream InputStream;
		//Listen for the connection from the socket;;;
		try {
			System.out.println("Creating InputStream for receiving data");
			InputStream = this.ClientSocket.getInputStream();
			this.ClientSocket.getOutputStream().write("Your name?? Maximum 20 bytes and must end with * \n".getBytes());
			//Getting the UserID data from client.. The data should be maximum of 20 bytes and minimum of 5 bytes
			byte[] UserIDbytes = new byte[USERIDBYTES];
			System.out.println("Receiving the Data");
			InputStream.read(UserIDbytes);
			String temp = Utilities.toString(UserIDbytes);
			log(temp);
			
			if (temp.matches("\\w[\\w\\d]{6,}\\*\\s*")) 
				{
				temp = temp.substring(0, temp.indexOf('*'));
				//Now validating temp
				log("Validating..... " + temp);
					if(ClientManager.validateUser(temp))
					{
						this.UserID =temp ;
					return true;
					}
					return false;
				};
			//Validating UserID bytes
		}catch(IOException ex){
			
		}

		
		
		return false;
	
	
	}
	
	
	
	
	
	
	
	

	public void run(){
		Success = this.getUserID() ;
		
		if(Success){
			System.out.println("Success!! Now creating another thread to handle the connections");
			ListenForConnection listen = new ListenForConnection(this);
			listen.start();
			ClientManager.addClients(this);
		}else {
			cleanup();
		}
		
	}
	
	
	
	
	
	
	
	
	private void cleanup(){
		if(ClientSocket !=null){
			try {
				this.ClientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		//delete this client from Online Lists
		ClientManager.deleteClient(this);
	}
	
	
	
	
	
	
	
	
	
	
	public Socket getSocket(){
		return this.ClientSocket;
	}
	
	
	
	
	
	
	public String UserID(){
		return this.UserID;
	}
	
	
	
	
	
	
	public boolean isJava(){
		return this.isJAVA;
	}

	
	
	
	
	
	public int getUserHash() {
		// TODO Auto-generated method stub
		return this.UserHash;
	}
}
