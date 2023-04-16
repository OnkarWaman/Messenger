

import java.sql.ResultSet;
import java.awt.Component;
import javax.swing.JOptionPane;

class LoginVerification
{
    static void loginVerificationProcess() throws Exception {
        final String user = LoginPage.usernameField.getText();
        final String pass = new String(LoginPage.passwordField.getPassword());
        final ResultSet rs = DbConection.stmt.executeQuery("select count(*) from user where username='" + user + "' and password='"+pass+"'");
        rs.next();
        if (rs.getInt("count(*)")==1) {
                JOptionPane.showMessageDialog(null, "Login successful");
        }
        else if (rs.getInt("count(*)")==0){
            JOptionPane.showMessageDialog(null, "incorrect credentials");
        }
    }
}