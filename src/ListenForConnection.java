import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import static t.log.* ;

public class ListenForConnection extends Thread{
	
	private Socket SocketToListenFor ;
	private Clients client;
	/*
	 * Constructor which takes Socket class as argument...
	 */
	public ListenForConnection(Clients Client){
		this.client = Client;
		this.SocketToListenFor = Client.getSocket();
		try {
			this.SocketToListenFor.setSoTimeout(0);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Function to listen for data stream in the socket
	 */
	private void listenfordata() {
		boolean CONTINUE = true;

		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(this.SocketToListenFor.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			CONTINUE = false;
		}
		System.out.println();
		int i ;
		while(CONTINUE){
//		try {
//			StringBuilder str = new StringBuilder();
//			while((i = reader.read()) != -1){
//				
//				System.out.print((char) i );
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			
//		}
			
			byte[] data = new byte[50] ;
			try{
				int ij = this.SocketToListenFor.getInputStream().read(data);
				if(ij == -1){
					CONTINUE = false;
				}
			}catch(IOException ex){
				CONTINUE=false;
			}
//		CONTINUE = false;
		String input = Utilities.toString(data);
		ClientManager.broadCastMessage(input);
		log(input);
		}
		System.out.println("Socket not responding...");
		ClientManager.deleteClient(this.client);
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		System.out.println("Listening for data now!!");
		listenfordata();
	}
}
