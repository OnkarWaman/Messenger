package messagepage;

import loginpage.*;
import cipher.Encryption;
import java.sql.PreparedStatement;


public class MsgInsertToDatabase {
	public static PreparedStatement msgInsertToDatabasePstmt;
	static int flag=0;
	static String msgToBeInserted;
	
	
	public static void msgInsertToDatabase() throws Exception {
		
		if(!MessagePage.msgSendTextArea.getText().isEmpty()) {
			if(flag==0) {
				flag++;
				msgInsertToDatabasePstmt = DbConection.con.prepareStatement("insert into msginfo values(now(),?,?)");
				
			}
        	
			msgToBeInserted = MessagePage.msgSendTextArea.getText();
			msgInsertToDatabasePstmt.setString(1, Encryption.encrypt(MessagePage.loginedUsersName));
			msgInsertToDatabasePstmt.setString(2, Encryption.encrypt(msgToBeInserted));
			msgInsertToDatabasePstmt.executeUpdate();
			System.out.println("value inserted");
			MessagePage.msgSendTextArea.setText(null);
		}	
	}	
}
