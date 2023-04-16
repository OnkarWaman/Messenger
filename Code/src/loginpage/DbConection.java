package loginpage;

import messagepage.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class DbConection
{
	public static Connection con;
	public static PreparedStatement loginPstmt;
    
    
    public static void connectToDatabase() throws Exception {
    	if(LoginVerification.isLogined==false) {
           
                try {
                	Properties pr = new Properties();
                	pr.load(new FileInputStream("config/key.properties"));
                	String url = pr.getProperty("url");
                	String username = pr.getProperty("username");
                	String password = pr.getProperty("password");
                	
                    DbConection.con = DriverManager.getConnection(url, username, password);
                    LoginPage.dbLabel.setIcon(LoginPage.dbConnectedImage);
                    DbConection.loginPstmt = DbConection.con.prepareStatement("select count(*) from user where username=? and password=?");
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
                catch (FileNotFoundException e) {
                	JOptionPane.showMessageDialog(null, "Error, config file not found...");
                	LoginPage.frame.dispose();
				}
    	}
    	else if (LoginVerification.isLogined==true) {
    		NetConnection.checkConnection();
            if (NetConnection.isConnected) {
                try {
                    DbConection.con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/onkarwaman", "onkarwaman", "onkarwaman");
                    MessagePage.dbLabel.setIcon(MessagePage.dbConnectedImage);
                    DbConection.loginPstmt = DbConection.con.prepareStatement("select count(*) from user where username=? and password=?");
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
		}
    	
    }
}