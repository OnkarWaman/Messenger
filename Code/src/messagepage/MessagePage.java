package messagepage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import loginpage.*;
import javax.swing.ImageIcon;
import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import loginpage.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessagePage extends JFrame {

	private JPanel contentPane;
	public static JLabel nameLabel;
	public static JLabel netLabel;
	public static ImageIcon netConnectedImage;
	public static ImageIcon netDisconnectedImage;
	public static JLabel dbLabel;
	public static ImageIcon dbConnectedImage;
	public static ImageIcon dbDisconnectedImage;
	public static JTextArea msgReceiveTextArea;
	public static JTextArea msgSendTextArea;
	public static JButton msgSendButton;
	public static ImageIcon msgSendButtonImage;
	private JScrollPane msgReceiveScrollPane;
	private JScrollPane scrollPane;
	public static String loginedUsersName;

	
	public MessagePage() throws Exception {
		getContentPane().setBackground(new Color(192,192,192));
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));
		setTitle("MessagePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 560, 627);
		
	
		ResultSet rs = DbConection.loginPstmt.executeQuery("select name from user where username='"+LoginVerification.user+"'");
		rs.next();
		loginedUsersName = rs.getString("name");
		getContentPane().setLayout(null);
		nameLabel = new JLabel("Hey "+loginedUsersName);
		nameLabel.setBounds(10, 10, 254, 31);
		nameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		getContentPane().add(nameLabel);
		
		netLabel = new JLabel("");
		netLabel.setBounds(400, 10, 51, 41);
		getContentPane().add(netLabel);
		
		dbLabel = new JLabel("");
		dbLabel.setBounds(472, 10, 51, 41);
		getContentPane().add(dbLabel);
		
		msgSendButton = new JButton("");
		msgSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgInsertToDatabase.msgInsertToDatabase();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		msgSendButton.setBounds(488, 517, 48, 48);
		msgSendButton.setBackground(new Color(255, 255, 255));
		getContentPane().add(msgSendButton);
		
		msgReceiveScrollPane = new JScrollPane();
		msgReceiveScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		msgReceiveScrollPane.setBounds(10, 76, 513, 397);
		getContentPane().add(msgReceiveScrollPane);
		
		msgReceiveTextArea = new JTextArea();
		msgReceiveTextArea.setEditable(false);
		msgReceiveScrollPane.setViewportView(msgReceiveTextArea);
		msgReceiveTextArea.setBackground(new Color(255, 255, 255));
		msgReceiveTextArea.setLineWrap(true);
		msgReceiveTextArea.setFont(new Font("Calibri", Font.PLAIN, 20));
		msgReceiveTextArea.setWrapStyleWord(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 507, 462, 66);
		getContentPane().add(scrollPane);
		
		msgSendTextArea = new JTextArea();
		scrollPane.setViewportView(msgSendTextArea);
		msgSendTextArea.setLineWrap(true);
		msgSendTextArea.setToolTipText("");
		msgSendTextArea.setDragEnabled(true);
		msgSendTextArea.setFont(new Font("Calibri", Font.PLAIN, 22));
		msgSendTextArea.setWrapStyleWord(true);
		
		
		netConnectedImage = new ImageIcon("icons/netConnectedImage.jpg");
		netDisconnectedImage = new ImageIcon("icons/netDisconnectedImage.jpg");
		dbConnectedImage = new ImageIcon("icons/dbConnectedImage.jpg");
		dbDisconnectedImage = new ImageIcon("icons/dbDisconnectedImage.jpg");
		MessagePage.netLabel.setIcon(MessagePage.netConnectedImage);
        MessagePage.dbLabel.setIcon(MessagePage.dbConnectedImage);
		
		msgSendButtonImage = new ImageIcon("icons/msgSendButtonImage.jpg");
		MessagePage.msgSendButton.setIcon(MessagePage.msgSendButtonImage);
		
	}
}