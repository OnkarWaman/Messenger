package messagepage;
import loginpage.*;

public class NetAndDbControllerForMessagePage {
	public static void start() throws Exception {
        while (true) {
        	NetConnection.checkConnection();
        	
            if (!NetConnection.isConnected) {
                DbConection.con = null;
                MessagePage.netLabel.setIcon(MessagePage.netDisconnectedImage);
                MessagePage.dbLabel.setIcon(MessagePage.dbDisconnectedImage);
                
            }
            
            if (NetConnection.isConnected && DbConection.con == null) {
                MessagePage.netLabel.setIcon(MessagePage.netConnectedImage);
                MessagePage.dbLabel.setIcon(MessagePage.dbDisconnectedImage);
                DbConection.connectToDatabase();
            }
           
            if (NetConnection.isConnected && DbConection.con != null) {
                MessagePage.netLabel.setIcon(MessagePage.netConnectedImage);
                MessagePage.dbLabel.setIcon(MessagePage.dbConnectedImage);
                
            }
            MsgRetrieveFromDatabase.msgRetrieveFromDatabase();
            Thread.sleep(2000);
            
        }
    }
}
