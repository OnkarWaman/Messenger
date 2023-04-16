package loginpage;

import messagepage.*;
import java.sql.ResultSet;
import java.awt.Component;
import javax.swing.JOptionPane;

import messagepage.MessagePage;

public class LoginVerification
{
	public static String user;
	public static boolean isLogined=false;
    static void loginVerificationProcess() throws Exception {
        user = LoginPage.usernameField.getText();
        final String pass = new String(LoginPage.passwordField.getPassword());
        DbConection.loginPstmt.setString(1, user);
        DbConection.loginPstmt.setString(2, pass);
        final ResultSet rs = DbConection.loginPstmt.executeQuery();
        rs.next();
        if (rs.getInt("count(*)")==1) {
//                JOptionPane.showMessageDialog(null, "Login successful");
                isLogined=true;
                
                MessagePage frame = new MessagePage();
				frame.setVisible(true);
				LoginPage.frame.dispose();
			    //
                
        }
        else if (rs.getInt("count(*)")==0){
            JOptionPane.showMessageDialog(null, "incorrect credentials");
        }
    }
}