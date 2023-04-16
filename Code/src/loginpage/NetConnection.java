package loginpage;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetConnection
{
    public static boolean isConnected;
    
    static {
        NetConnection.isConnected = false;
    }
    
    public static void checkConnection() {
        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress("www.google.com", 80), 1000);
            socket.close();
            NetConnection.isConnected = true;
        }
        catch (IOException e) {
            NetConnection.isConnected = false;
            
        }
    }
}