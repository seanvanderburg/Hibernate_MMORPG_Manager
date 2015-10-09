import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.postgresql.util.PSQLException;

import entities.Player;

//frontend and user operations
public class Front {

	static String username;
	static String password;
	static String money;
	static int moneychange;
	static int months;
	static int slots;
	static String selectedClass;
	static String selectedRace;
	
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
	JButton addmoney = new JButton("Add money");
	JButton addslots = new JButton("Buy one character-slot");
	JButton backacc = new JButton("Back");
	JButton backchar = new JButton("Back");


	static CardLayout cl = new CardLayout();

	static JLabel loginnotify = new JLabel();
	JLabel text1 = new JLabel("Enter username:");
	JLabel text2 = new JLabel("Enter password:");
	static JLabel loggedIn = new JLabel();
	JLabel moneyText = new JLabel("Add money:");
	JTextField moneyInput = new JTextField("", 15);
	JLabel moneyIndicator = new JLabel();
	JLabel monthsIndicator = new JLabel();
	JTextField passwinput = new JTextField("", 15);
	JTextField userinput = new JTextField("", 15);
	JTextField subscrtext = new JTextField(15);
	JButton getItembutton = new JButton("Add months");
	static JLabel abortpayment = new JLabel();
	JLabel slotsindicator = new JLabel();
	JLabel slotsindicatorB = new JLabel();
	JButton selectChar = new JButton("Create char");
	
	String[] subscriptions = new String[] { "1 month", "2 months", "3 months","1 year" };
	JComboBox<String> subscription = new JComboBox<String>(subscriptions);
	
	String[] characterclasses = new String[] { "Fighter", "Rogue", "Mage"};
	JComboBox<String> characterclass = new JComboBox<String>(characterclasses);

	String[] characterraces = new String[] { "Male", "Female"};
	JComboBox<String> characterrace = new JComboBox<String>(characterraces);
	
	

	public Front() {
		panelCont.setLayout(cl);

		// login elements
		panelLogin.setBorder(BorderFactory
				.createEmptyBorder(100, 100, 100, 100));
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
		panelMenu.add(loggedIn);

		// account elements
		panelAccount.add(moneyIndicator);
		panelAccount.add(moneyText);
		panelAccount.add(moneyInput);
		panelAccount.add(addmoney);
		panelAccount.add(monthsIndicator);
		panelAccount.add(subscription);
		panelAccount.add(getItembutton);
		panelAccount.add(abortpayment);
		panelAccount.add(slotsindicator);
		panelAccount.add(addslots);
		panelAccount.add(backacc);

		
		// character elements
		panelCharacter.add(characterclass);
		panelCharacter.add(characterrace);
		panelCharacter.add(selectChar);
		panelCharacter.add(slotsindicatorB);
		panelCharacter.add(backchar);
		
		// main container
		panelCont.add(panelLogin, "1");
		panelCont.add(panelMenu, "2");
		panelCont.add(panelCharacter, "3");
		panelCont.add(panelAccount, "4");

		cl.show(panelCont, "1");

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					username = userinput.getText();
					password = passwinput.getText();
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
		
		backacc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});
		
		backchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});

		characters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "3");
				slotsindicatorB.setText("Current amount of Character-slots:" + App.getSlots());
			}
		});

		account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "4");
				moneyIndicator.setText("Current money balance: $"+ App.getPlayerBalance());
				monthsIndicator.setText("Amount of subscribed months: " + App.getSubscribedMonths());
				slotsindicator.setText("Current amount of Character-slots: " + App.getSlots());

			}
		});

		addmoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				money = moneyInput.getText();
				App.addMoney();
				moneyIndicator.setText("Current money balance: $"
						+ App.getPlayerBalance());

			}
		});

		addslots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				App.addSlots();
				moneyIndicator.setText("Current money balance: $"+ App.getPlayerBalance());
				slotsindicator.setText("Current amount of Character-slots: " + App.getSlots());

			}
		});

		getItembutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedsub = (String) subscription.getSelectedItem();
				if (selectedsub == "1 month") {
					moneychange = 5;
					months = 1;
				}
				if (selectedsub == "2 months") {
					moneychange = 8;
					months = 2;

				}
				if (selectedsub == "3 months") {
					moneychange = 10;
					months = 3;

				}
				if (selectedsub == "1 year") {
					moneychange = 35;
					months = 4;

				}
			App.updatePayment();
			moneyIndicator.setText("Current money balance: $"+ App.getPlayerBalance());
			monthsIndicator.setText("Amount of subscribed months:" + App.getSubscribedMonths());
			}
		});

		selectChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectedClass = (String) characterclass.getSelectedItem();
				selectedRace = (String) characterrace.getSelectedItem();
				App.addCharacter();
			slotsindicatorB.setText("Current amount of Character-slots: " + App.getSlots());
			}
		});
		
		
		
		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		panelLogin.setSize(50, 50);
		panelMenu.setSize(50, 50);
		panelAccount.setSize(50, 50);
		panelCharacter.setSize(50, 50);
		frame.setSize(400, 400);
	}

	public static void toggleMenu() {
		cl.show(panelCont, "2");
		loggedIn.setText("Logged in as: " + getUsernameInput());
	}

	public static void addIncorrectMessage() {
		loginnotify.setText("Username or password incorrect");
	}

	public static void removeMessage() {
		loginnotify.setText(null);
		abortpayment.setText(null);
	}

	public static void addUsedMessage() {
		loginnotify.setText("Username or password already in use");
	}

	public static void addConfirmMessage() {
		loginnotify.setText("New user is now registered");
	}
	public static void addPurchaseAbortMessage() {
		abortpayment.setText("No money! Purchase aborted. Please load more money.");
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