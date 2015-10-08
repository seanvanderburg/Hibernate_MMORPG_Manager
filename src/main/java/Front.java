import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.postgresql.util.PSQLException;

public class Front {
	JFrame frame = new JFrame("Java MMORPG app");
	static JPanel panelCont = new JPanel();
	JPanel panelLogin = new JPanel();
	JPanel panelMenu = new JPanel();
	JPanel panelCharacter = new JPanel();
	JPanel panelAccount = new JPanel();
	JButton register = new JButton("Register");
	JButton login = new JButton("Login");
	JButton characters = new JButton("Characters");
	JButton account = new JButton("Account");
	static CardLayout cl = new CardLayout();

	static JLabel loginnotify = new JLabel();
	JLabel text1 = new JLabel("Voer gebruikersnaam in:");
	JLabel text2 = new JLabel("Voer wachtwoord in:");
	JTextField passwinput = new JTextField("", 15);
	JTextField userinput = new JTextField("", 15);

	static String username;
	static String password;

	public Front() {
		panelCont.setLayout(cl);

		// login elements
		panelLogin.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panelLogin.add(register);
		panelLogin.add(login);
		panelLogin.add(text1);
		panelLogin.add(userinput);
		panelLogin.add(text2);
		panelLogin.add(passwinput);
		panelLogin.add(register);
		panelLogin.add(login);
		panelLogin.add(loginnotify);

		// menu elements
		panelMenu.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panelMenu.add(characters);
		panelMenu.add(account);
		// container

		panelCont.add(panelLogin, "1");
		panelCont.add(panelMenu, "2");
		panelCont.add(panelCharacter, "3");
		panelCont.add(panelAccount, "4");
		
		cl.show(panelCont, "1");

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username = userinput.getText();
				password = passwinput.getText();
				try {
					App.registerUser();
				} catch (PSQLException e) {

				}

			}
		});

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username = userinput.getText();
				password = passwinput.getText();
				App.login();
			}
		});
		
		characters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "3");
			}
		});
		
		account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "4");
			}
		});
		
		
		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		panelLogin.setSize(50, 50);
		panelMenu.setSize(50, 50);
		frame.setSize(400, 400);
	}

	public static void toggleMenu() {
		cl.show(panelCont, "2");
	}

	public static void addIncorrectMessage() {
		loginnotify.setText("Username of wachtwoord niet correct");
	}

	public static void removeMessage() {
		loginnotify.setText("");
	}


	public static void addUsedMessage() {
		loginnotify.setText("Username of wachtwoord is al in gebruik");
	}

	public static void addConfirmMessage() {
		loginnotify.setText("Gebruiker is geregristreerd");
	}

	public static String getPasswInput() {
		return password;
	}

	public static String getUsernameInput() {
		return username;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Front();
			}
		});
	}

}