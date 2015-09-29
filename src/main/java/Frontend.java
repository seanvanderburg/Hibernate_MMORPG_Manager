import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.postgresql.util.PSQLException;

public class Frontend extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4251702363640218639L;
	static JPanel mainpnl;
	static JPanel loginpnl;
	static JPanel menupnl;
	JButton loginB;
	JButton registerB;
	public static JLabel message;
	JLabel text1;
	JLabel text2;
	JTextField passwinput;
	JTextField userinput;
	private static String username;
	static String password;
 
	public Frontend() {
		mainpnl = new JPanel();
		loginpnl = new JPanel();
		menupnl = new JPanel();
		loginB = new JButton("Inloggen");
		registerB = new JButton("Registreren");
		userinput = new JTextField("", 15);
		text1 = new JLabel("Voer gebruikersnaam in:");
		passwinput = new JTextField("", 15);
		text2 = new JLabel("Voer wachtwoord in:");
		message = new JLabel();
		registerB.addActionListener(this);
		loginB.addActionListener(this);

		FlowLayout experimentLayout = new FlowLayout();
		Font bigFont = mainpnl.getFont().deriveFont(Font.PLAIN, 15f);
		userinput.setFont(bigFont);
		passwinput.setFont(bigFont);
		loginpnl.setPreferredSize(new Dimension(400, 400));
		mainpnl.add(loginpnl);
        loginpnl.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

		this.add(mainpnl);
		loginpnl.setLayout(experimentLayout);
		loginpnl.add(text1);
		loginpnl.add(userinput);
		loginpnl.add(text2);
		loginpnl.add(passwinput);
		loginpnl.add(loginB);
		loginpnl.add(registerB);
		loginpnl.add(message);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == loginB) {
			username = userinput.getText();
			password = passwinput.getText();
			App.login();
			//App.menu():
		}

		if (event.getSource() == registerB) {
			username = userinput.getText();
			password = passwinput.getText();
			try {
				App.registerUser();
			} catch (PSQLException e) {

			}			
		}
	}

	public static void addIncorrectMessage() {
		message.setText("Username of wachtwoord niet correct");
	}
	
	public static void removeMessage() {
		message.setText("");
	}

	public static void addUsedMessage() {
		message.setText("Username of wachtwoord is al in gebruik");
	}
	public static void addConfirmMessage() {
		message.setText("Gebruiker is geregristreerd");
	}
	
	
	public static String getPasswInput() {
		return password;
	}

	public static String getUsernameInput() {
		return username;
	}

}