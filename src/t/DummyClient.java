package t;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static t.log.* ; 
public class DummyClient {

	static BufferedWriter write ;
	static BufferedReader Read ;
	public static void main(String[] args)
	{
		boolean registered=  false;
		int port = 2555 ;
		System.out.println("Hello how are yoi??") ;
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		String command ="";
		try {
			command = read.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(command.startsWith("connect")) 
		{
			registered = true ;
			String[] com =  command.split("\\s");
			log(com[1]);
			if(com.length != 3) 
			{
				log("failed...");
				return ;
			}
			String host = com[1] ;
			try {
			port = Integer.parseInt(com[2]);
			}catch(Exception ex)
			{
				
			}
			//Attempting Comnection...
			log("Attemption Connection!!!");
			Socket soc =null;
			try {
				soc = new Socket(host,port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				write = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Read = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log("Creating thread for listening to connection");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					boolean TRUE = true;
					while(TRUE){
					System.out.println() ;
					int i = 0 ;
					try {
						while((i = Read.read() ) != -1 )
						{
							System.out.print((char)i);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						TRUE = false;
						e.printStackTrace();
					}
					System.out.println() ;
					}
					
				}
			}).start() ;
		}
		while(registered){
			command = "";
			try {
				command = read.readLine() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break ;
			}
			processCommand(command) ;
		}
	}

	private static void processCommand(String command) {
		// TODO Auto-generated method stub
		try {
			write.write(command);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			write.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
