package messagepage;

import cipher.Decryption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import loginpage.*;

public class MsgRetrieveFromDatabase {
	public static PreparedStatement msgRetrieveFromDatabaseForFirstTimePstmt;
	public static PreparedStatement msgRetrieveFromDatabaseForOtherTimesPstmt;
	static int flag1=0;
	static int flag2=0;
	static String lastMsgDateAndTime=null;
	static ResultSet rs;
	public static void msgRetrieveFromDatabase() throws Exception {
		
		if(flag1==0) {
			flag1++;
			msgRetrieveFromDatabaseForFirstTimePstmt = DbConection.con.prepareStatement("select name,dateandtime,message from msginfo");
			rs = msgRetrieveFromDatabaseForFirstTimePstmt.executeQuery();
			while(rs.next()) {
				printMessagesToMessageReceiveTextBox();
			}
		}
		else {
			if(lastMsgDateAndTime!=null) {
				if(flag2==0) {
					msgRetrieveFromDatabaseForOtherTimesPstmt = DbConection.con.prepareStatement("select name,dateandtime,message from msginfo where dateandtime > ?");
					flag2++;
				}
				
				msgRetrieveFromDatabaseForOtherTimesPstmt.setString(1, lastMsgDateAndTime);
				rs = msgRetrieveFromDatabaseForOtherTimesPstmt.executeQuery();
				while(rs.next()) {
					printMessagesToMessageReceiveTextBox();
				}
			}
			else {
				msgRetrieveFromDatabaseForFirstTimePstmt = DbConection.con.prepareStatement("select name,dateandtime,message from msginfo");
				rs = msgRetrieveFromDatabaseForFirstTimePstmt.executeQuery();
				while(rs.next()) {
					printMessagesToMessageReceiveTextBox();
				}
			}
		}
	}
	private static void printMessagesToMessageReceiveTextBox() throws Exception {
		String temp1 = MessagePage.msgReceiveTextArea.getText();
		String  temp2 = Decryption.decrypt(rs.getString(1))+"\t"+rs.getString(2)+"\n"+Decryption.decrypt(rs.getString(3))+"\n\n";
		MessagePage.msgReceiveTextArea.setText(temp1.concat(temp2));
		lastMsgDateAndTime =  rs.getString(2);
	}
}
