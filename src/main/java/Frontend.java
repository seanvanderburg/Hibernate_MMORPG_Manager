import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Frontend extends JFrame implements ActionListener {

	static JPanel mypanel;
	JButton loginB;
	JButton registerB;	
	public static JLabel incorrect;
	JTextField passwinput;
	JTextField userinput;
	private static String username;
	static String password;

	public Frontend() {
		mypanel = new JPanel();
		loginB = new JButton("Inloggen");
		registerB = new JButton("Regristreren");
		userinput = new JTextField("", 15);
		passwinput = new JTextField("", 15);
		incorrect = new JLabel();
		registerB.addActionListener(this);
		loginB.addActionListener(this);
		
		FlowLayout experimentLayout = new FlowLayout();
		Font bigFont = mypanel.getFont().deriveFont(Font.PLAIN, 15f);
		userinput.setFont(bigFont);
		passwinput.setFont(bigFont);		
		this.add(mypanel);
		mypanel.setLayout(experimentLayout);
		mypanel.add(loginB);
		mypanel.add(registerB);
		mypanel.add(userinput);
		mypanel.add(passwinput);
		mypanel.add(incorrect);

	}

	public void actionPerformed(ActionEvent event) {
		username = userinput.getText();
		password = passwinput.getText();
		App.login();

	}
	
	public static void addIncorrectMessage(){
		incorrect.setText("Username of wachtwoord niet correct");
	}
	
	public static String getPasswInput(){
		return password;
	}
	
	public void setPasswInput(String a){
		Frontend.password = a;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Frontend.username = username;
	}

	
	
}