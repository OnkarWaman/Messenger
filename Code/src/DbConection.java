

import java.sql.SQLException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

class DbConection
{
    static Statement stmt;
    static Connection con;
    
    static void connectToDatabase() throws Exception {
    	NetConnection.checkConnection();
        if (NetConnection.isConnected) {
            try {
                LoginPage.connectionStatusLabel.setText("Connecting to Database");
                DbConection.con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/onkarwaman", "onkarwaman", "onkarwaman");
                LoginPage.connectionStatusLabel.setText("Database Connected");
                DbConection.stmt = DbConection.con.createStatement();
            }
            catch (SQLException e) {
            	NetConnection.checkConnection();
            	if(NetConnection.isConnected==false) {
            		JOptionPane.showMessageDialog(null, "Internet disrupted while connecting to database, try again later");
                    LoginPage.frame.dispose();
            	}
            	else {
            		  JOptionPane.showMessageDialog(null, "Database Error , try again later");
                      LoginPage.frame.dispose();
            	}
              
            }
        }
        Thread.sleep(2000);
    }
}