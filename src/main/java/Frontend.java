import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frontend extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4251702363640218639L;
	static JPanel mypanel;
	static JPanel pnl;

	JButton loginB;
	JButton registerB;
	public static JLabel incorrect;
	JLabel text1;
	JLabel text2;
	JTextField passwinput;
	JTextField userinput;
	private static String username;
	static String password;

	public Frontend() {
		mypanel = new JPanel();
		pnl = new JPanel();
		loginB = new JButton("Inloggen");
		registerB = new JButton("Registreren");
		userinput = new JTextField("", 15);
		text1 = new JLabel("Voer gebruikersnaam in:");
		passwinput = new JTextField("", 15);
		text2 = new JLabel("Voer wachtwoord in:");
		incorrect = new JLabel();
		registerB.addActionListener(this);
		loginB.addActionListener(this);

		FlowLayout experimentLayout = new FlowLayout();
		Font bigFont = mypanel.getFont().deriveFont(Font.PLAIN, 15f);
		userinput.setFont(bigFont);
		passwinput.setFont(bigFont);
		pnl.setPreferredSize(new Dimension(250, 200));
		mypanel.add(pnl, BorderLayout.CENTER);
		this.add(mypanel);
		pnl.setLayout(experimentLayout);
		pnl.add(text1);
		pnl.add(userinput);
		pnl.add(userinput);
		pnl.add(text2);
		pnl.add(passwinput);
		pnl.add(loginB);
		pnl.add(registerB);
		pnl.add(incorrect);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == loginB) {
			username = userinput.getText();
			password = passwinput.getText();
			App.login();
		}

		if (event.getSource() == registerB) {
			username = userinput.getText();
			password = passwinput.getText();
			App.registerUser();			
		}
	}

	public static void addIncorrectMessage() {
		incorrect.setText("Username of wachtwoord niet correct");
	}

	public static void addUsedMessage() {
		incorrect.setText("Username of wachtwoord is al in gebruik");
	}
	
	public static String getPasswInput() {
		return password;
	}

	public static String getUsernameInput() {
		return username;
	}

}