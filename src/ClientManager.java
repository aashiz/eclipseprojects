import java.io.IOException;
import java.util.ArrayList;


public class ClientManager {
	
	public static ArrayList<Clients> ClientList = new ArrayList<Clients>();
	public static ArrayList<String> UserList = new ArrayList<String>();
	public static void deleteClient(Clients client){
		System.out.println("Deleting Client!!");
		ClientList.remove(client);
	}
	
	public static void broadCastMessage(String Message){
		for(Clients  e :ClientList) 
		{
			try {
				e.getSocket().getOutputStream().write(Message.getBytes());
				e.getSocket().getOutputStream().flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void addClients(Clients e){
		String java = e.isJava() ? "JAVA" : "NONJAVA"  ;
		System.out.println("Adding the client " + e.UserID() + " " + java + "");
		ClientList.add(e);
		UserList.add(e.UserID() );
		System.out.println("Client Added!!!");
	}
	
	public static void listClients(){
		for(Clients x : ClientList){
			System.out.println(x.UserID() + "  ::  " + x.isJava());
		}
	}

	public static boolean search(int userHash) {
		// TODO Auto-generated method stub
		for(Clients x:ClientList){
			if(userHash == x.getUserHash()){
				return true; //Exists with the userHash
			}
		}
		return false;
		
		
		
	}
	
	public static void addUser(String user) 
	{
		
		UserList.add(user);
	}
	
	public static boolean validateUser(String user) 
	{
		for(String u : UserList)
		{
			if(u.equals(user)){
				return false;
			}
		}
		return true ;
		}
	}
	
