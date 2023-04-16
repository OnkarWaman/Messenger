

import java.io.IOException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

class NetConnection
{
    static boolean isConnected;
    
    static {
        NetConnection.isConnected = false;
    }
    
    static void checkConnection() {
        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress("www.google.com", 80), 1000);
            socket.close();
            System.out.println("Network Connected");
            NetConnection.isConnected = true;
        }
        catch (IOException e) {
            NetConnection.isConnected = false;
            System.out.println("Network disconnected");
        }
    }
}