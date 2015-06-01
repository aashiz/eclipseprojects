import java.io.IOException;
import java.io.InputStreamReader;


public class Utilities {

	
	private static String regex = "\\w[\\w\\d]{6,}\\*\\s*" ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = "" ;
			do 
			{
				System.out.println("Enter a string....");
				java.io.BufferedReader read = new java.io.BufferedReader(new InputStreamReader(System.in));
				try {
					line = read.readLine() ;
					System.out.println(isMatch(line));
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(!line.equals("quit" )) ;
	}	
	
	private static boolean isMatch(String s)
	{
		if(s.matches(regex)){
			return true ;
		}
	return false;
	}
	
	public static String toString(byte[] input)
	{
		StringBuilder str = new StringBuilder() ;
		int length = input.length ;
		
		for(int i=0 ; i<length;i++ )
		{
			byte s = input[i];
			if(!( s == 0 || s == 10 || s == 13 )){
				str.append((char)s);	
			}
			
		}
		
		return str.toString();
	}

}
