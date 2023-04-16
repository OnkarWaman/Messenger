
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Button;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JTextPane;

public class LoginPage extends JFrame {
	static JTextField usernameField;
	static JPasswordField passwordField;
	static LoginPage frame;
	static JLabel connectionStatusLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		
		frame = new LoginPage();
		frame.setVisible(true);
		NetAndDbController.start();
		
		
	}
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		getContentPane().setBackground(new Color(192,192,192));
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 560, 627);
		getContentPane().setLayout(null);
		
		connectionStatusLabel = new JLabel();
        connectionStatusLabel.setForeground(new Color(255, 0, 0));
        connectionStatusLabel.setFont(new Font("Tahoma", 1, 26));
        connectionStatusLabel.setBounds(105, 344, 404, 111);
        getContentPane().add(LoginPage.connectionStatusLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		usernameLabel.setBounds(59, 92, 99, 31);
		getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		passwordLabel.setBounds(59, 134, 99, 41);
		getContentPane().add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Calibri", Font.PLAIN, 17));
		usernameField.setBounds(168, 94, 214, 28);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				      // Do something when the Enter key is released
					 try {
						LoginVerification.loginVerificationProcess();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
			}
		});
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 17));
		passwordField.setBounds(168, 141, 214, 28);
		getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(DbConection.con!=null) {
						LoginVerification.loginVerificationProcess();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginButton.setBackground(UIManager.getColor("Button.light"));
		loginButton.setFont(new Font("Calibri", Font.ITALIC, 20));
		loginButton.setForeground(Color.BLACK);
		loginButton.setBounds(129, 230, 108, 41);
		getContentPane().add(loginButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				      // Do something when the Enter key is released
					 clearEvent();
				}
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearEvent();
			}
		});
		clearButton.setBackground(UIManager.getColor("Button.light"));
		clearButton.setForeground(Color.BLACK);
		clearButton.setFont(new Font("Calibri", Font.ITALIC, 20));
		clearButton.setBounds(261, 230, 108, 41);
		getContentPane().add(clearButton);
		
		JCheckBox showPassCheckbox = new JCheckBox("show password");
		showPassCheckbox.setFont(new Font("Calibri", Font.PLAIN, 12));
		showPassCheckbox.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPassCheckbox.setSelected(true);
				passwordField.setEchoChar((char)0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				showPassCheckbox.setSelected(false);
				passwordField.setEchoChar('•');
			}
		});
		showPassCheckbox.setBounds(168, 175, 116, 23);
		getContentPane().add(showPassCheckbox);
	}
	
	void clearEvent() {
		usernameField.setText(null);
		passwordField.setText(null);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}


