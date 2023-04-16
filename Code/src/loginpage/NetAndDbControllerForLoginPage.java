package loginpage;

import messagepage.*;

public class NetAndDbControllerForLoginPage
{
    public static void start() throws Exception {
    	NetConnection.checkConnection();
        if (NetConnection.isConnected) {
            LoginPage.netLabel.setIcon(LoginPage.netConnectedImage);
            LoginPage.dbLabel.setIcon(LoginPage.dbDisconnectedImage);
            DbConection.connectToDatabase();
        }
        else {
            LoginPage.netLabel.setIcon(LoginPage.netDisconnectedImage);
            LoginPage.dbLabel.setIcon(LoginPage.dbDisconnectedImage);
            DbConection.con=null;
        }
        
    	
        
        while (true) {
        	NetConnection.checkConnection();
            if (!NetConnection.isConnected) {
                DbConection.con = null;
                LoginPage.netLabel.setIcon(LoginPage.netDisconnectedImage);
                LoginPage.dbLabel.setIcon(LoginPage.dbDisconnectedImage);
            }
            if (NetConnection.isConnected && DbConection.con == null) {
                LoginPage.netLabel.setIcon(LoginPage.netConnectedImage);
                DbConection.connectToDatabase();
            }
            if (NetConnection.isConnected && DbConection.con != null) {
            	LoginPage.netLabel.setIcon(LoginPage.netConnectedImage);
            	LoginPage.dbLabel.setIcon(LoginPage.dbConnectedImage);
                
            }
            
            if(LoginVerification.isLogined) {
            	break;
            }
            Thread.sleep(2000);
            
        }
    }
}