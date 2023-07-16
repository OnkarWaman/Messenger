package messagepage;

import loginpage.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			msgInsertToDatabasePstmt.setString(1, MessagePage.loginedUsersName);
			msgInsertToDatabasePstmt.setString(2, msgToBeInserted);
			msgInsertToDatabasePstmt.executeUpdate();
			System.out.println("value inserted");
			MessagePage.msgSendTextArea.setText(null);
		}	
	}	
}
