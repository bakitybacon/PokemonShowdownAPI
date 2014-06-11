package connect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection 
{
	public static final int port = 80;
	public static final String hostname = "play.pokemonshowdown.com";
	boolean socketOpened = false;
	
	public static void main(String[]args) throws IOException, InterruptedException
	{
		Socket sock = new Connection().constructSocket();
		
		DataInputStream is = new DataInputStream(sock.getInputStream());
		
		System.out.println(sock);
		
		DataOutputStream os = new DataOutputStream(sock.getOutputStream());
		os.writeChars("/avatar 22,1");
		System.out.println("Wrote Join");
	
		int b = -1;
		while((b = is.read()) != -1)
		{
			System.out.print((char)b);
		}
		
		System.exit(0);
		
		os.writeChars("|lobby|/nick Magma Admin");
		System.out.println("Wrote Name");
		while((b = is.read()) != -2)
		{
			if(b == -1)
			{
				Thread.sleep(1000);
			}
			System.out.print((char)b);
		}
		
		//System.out.println("Wrote uname");
		//System.out.println(is.readLine());
	}
	
	private Socket constructSocket()
	{
		//always use https
		Socket c = null;
		try 
		{
			c = new Socket(hostname,port);

			socketOpened = true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return c;
	}
}
