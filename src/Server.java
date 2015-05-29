import java.net.*;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.*;
public class Server {

	static ServerSocket server;
	public static void main(String[] args){
		System.out.println("Starting Server at port 2555");
		try {
			server = new  ServerSocket(2555);
//			SystemTray tray =SystemTray.getSystemTray();
//			tray.add(new TrayIcon(null));
			InetAddress ea= InetAddress.getLocalHost();
			System.out.println(ea.toString());
			System.out.println("Server Started!!!" + server.getInetAddress());
			System.out.println(server.getLocalSocketAddress() + "--" +server.getLocalPort());
			System.out.println(server.getReuseAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Socket client;
		try {
			while(true){
			client = server.accept();
			Clients cl = new Clients(client);
			cl.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
