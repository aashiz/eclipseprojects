import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import t.log;
import static t.log.*;

public class Message {
	private String TO,FROM,MESSAGE ;
	public Message(String frm,String to,String message)
	{
		this.TO = to ;
		this.FROM = frm ;
		this.MESSAGE = message ;
	}

	public String createMessage() throws UnsupportedEncodingException 
	{
		String imessage = "from=" ;
		imessage = imessage +  URLEncoder.encode(this.FROM,"UTF-8") + "&" ;
		imessage = imessage + "to=" + URLEncoder.encode(this.TO,"UTF-8") + "&" + 
				"message=" + URLEncoder.encode(this.MESSAGE,"UTF-8");
		
		log("Decoding the one....");
		try {
			log(decodeMessage(imessage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imessage ;
	}
	public static Message decodeMessage(String rawMessage) 
	{
		try {
		String from = rawMessage.substring(rawMessage.indexOf("=")+1,rawMessage.indexOf("&"));
		//log(from);
		String to = rawMessage.substring(rawMessage.indexOf("to="));
		//log(to);
		to = to.substring(to.indexOf("=")+1, to.indexOf("&"));
		//log(to);
		String message = rawMessage.substring(rawMessage.indexOf("message="));
		//log(message);
		message = message.substring(message.indexOf("=") +1);//, endIndex)
		//log(message);
		from = URLDecoder.decode(from , "UTF-8");
		to =URLDecoder.decode(to , "UTF-8");
		message =URLDecoder.decode(message , "UTF-8");
		log("FROM = " + from + "\n" + "TO = " + to + "\n" + "MESSAGE = " + message + "\n");
		return new Message(from,to,message);
		}catch(IndexOutOfBoundsException ex){
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		
		
	}
	
	public static void main(String[] args) throws Exception
	{
		Message s = new Message("Aashish@gmail.com" , "brsh@gmail.com " , "I love you baby !!!&&## " );
		s.createMessage();
		//Message.decodeMessage("from=aasihs&to=asshjhk&message=helloworld");
	}
}
