import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.postgresql.util.PSQLException;

import entities.Character;
import entities.Player;
import entities.Server;

//database related operations
public class App {

	public static void login() {
		//check if user is present in db, if the user data is found toggle the main menu in the frontend
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();

		session.beginTransaction();
		Front.removeMessage();
		List result = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Player where username = ? OR password = ?")
				.setString(0, Front.getUsernameInput())
				.setString(1, Front.getPasswInput()).list();
		
		if (result.isEmpty() || result == null) {
			System.out.println(result);
			Front.addIncorrectMessage();
			session.getTransaction().commit();
		} else {
			System.out.println("Login data:" + result);
			session.getTransaction().commit();
			Front.toggleMenu();

		}
	}
	

	public static void registerUser() throws PSQLException {
		//create a new account for a user (instantiate a user entity)
		Session session = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		Front.removeMessage();
		List<?> checkresult = HibernateUtil.getSessionFactory()
				.getCurrentSession()
				.createQuery("from Player where username = ? OR password = ?")
				.setString(0, Front.getUsernameInput())
				.setString(1, Front.getPasswInput()).list();

		if (checkresult.isEmpty()) {
			Player player = new Player();
			player.setUsername(Front.getUsernameInput());
			player.setPassword(Front.getPasswInput());
			player.setCharacterslots(5);
			player.setBalance(0);
			player.setFirstname(getRandomString());
			player.setLastname(getRandomString());
			session.save(player);
			System.out.println("Player saved with ID" + player.getId());
			Front.addConfirmMessage();
			session.getTransaction().commit();
		} else {
			Front.addUsedMessage();
			session.getTransaction().commit();
		}
	}
	
	public static void addMoney(){
		//add money for a user (user-specified amount)
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		Front.removeMessage();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		
		System.out.println(player.toString());
		System.out.println(Front.money);
		double enteredMoney = Double.parseDouble(Front.money);
		System.out.println(enteredMoney);
		double currentMoney = player.getBalance();
		double totalmoney = currentMoney + enteredMoney;
		player.setBalance(totalmoney);
		System.out.println(player.toString());
		session.update(player);
		session.getTransaction().commit();
		
	}
	
	public static void addSlots(){
		//add one extra character slot for a user
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		
		if(player.getBalance() > 0 ){
			int currentMoney = (int) (player.getBalance() - 1);
			player.setBalance(currentMoney);
			Date date = new Date();
			player.setLastpayment(date.toString());
			
			int currentslots = player.getCharacterslots(); 
			player.setCharacterslots(currentslots + 1);
			session.getTransaction().commit();

		}
		else{
			Front.addPurchaseAbortMessage();
			session.getTransaction().commit();
		}
		
	}
	
	public static int getSlots(){
		//get the character slots the user has
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		int currentSlots = player.getCharacterslots();
		session.getTransaction().commit();
		return currentSlots;
	}
	
	
	public static double getPlayerBalance(){
		//get the current money balance the user has
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		double currentMoney = player.getBalance();
		session.getTransaction().commit();
		return currentMoney;
	}
	
	public static double getSubscribedMonths(){
		//get the amount of months a certain user has subscribed to
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		double currentMonths = player.getMonthspayed();
		session.getTransaction().commit();
		return currentMonths;
	}
	
	public static void addCharacter(){
		//creates a character for a user, based on user input
		Front.removeMessage();
		Session session = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		
		int currentslots = player.getCharacterslots(); 	
		
		if(currentslots > 0){
		entities.Character character = new entities.Character();
		character.setPlayer(player);
		character.setRace(Front.selectedRace);
		character.setCharClass(Front.selectedClass);
		character.setLevel(getRandomInt());
		character.setName(getRandomString());
		player.setCharacterslots(currentslots - 1);
		session.save(player);
		session.save(character);
		session.getTransaction().commit();
		}
		else{
		System.out.println("Not enough slots");	
		session.getTransaction().commit();
		Front.addFullSlots();
		}
	}
	
	public static ArrayList getCharacters(){
		//get class and race data for characters the user has
		Front.removeMessage();
		Session session = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		ArrayList<String> characterNames = new ArrayList<String>();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		List<entities.Character> characters = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Character where player = ? order by level")
				.setLong(0, player.getId()).list();
		
		if (characters.isEmpty() || characters == null) {
			System.out.println(characters);
			session.getTransaction().commit();
		} else {
			System.out.println("This player has these characters: " + characters);
			
			for(entities.Character character : characters) {
				characterNames.add(character.getRace() + " " + character.getCharClass());
				
		    }
			session.getTransaction().commit();

		}
		return characterNames;
		
		
	}
	
	public static List<Character> getCharactersDetails(){
		//get all details for the characters the current user hass
		Front.removeMessage();
		Session session = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		List<entities.Character> characters = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Character where player = ? order by level")
				.setLong(0, player.getId()).list();
		
			session.getTransaction().commit();
		
		return characters;
		
	}
	
	public static void updatePayment(){
		//update the subscription data for the player
		double moneychange = Front.moneychange;
		int monthssubscribed = Front.months;
		System.out.println(moneychange);
		Front.removeMessage();
		
		Session session = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		if(player.getBalance() > 0 ){
			int currentMoney = (int) (player.getBalance() - moneychange);
			player.setBalance(currentMoney);
			Date date = new Date();
			player.setLastpayment(date.toString());
			
			int currentMonths = player.getMonthspayed() + monthssubscribed; 
			player.setMonthspayed(currentMonths);
			session.getTransaction().commit();
			System.out.println(currentMoney);
		}
		else{
			Front.addPurchaseAbortMessage();
			session.getTransaction().commit();
		}
	}
	
	public static int getRandomInt(){
		//get random int
		return (int)(Math.random()*100);
	}
	
	public static String getRandomString(){
		//get random string
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	public static void joinServer(){
		//attaches the user to a server record
		Front.removeMessage();
		Session session = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		
		
		Server server = new Server();
		if(server.getConnectedusers() < 18){
	    server.getPlayers().add(player);

	    player.getServers().add(server);
		server.setAdress(getRandomString());
		server.setLocation(getRandomString());
		server.setName(getRandomString());
		server.setConnectedusers(server.getConnectedusers() + 1);

		session.save(player);
		session.save(server);
		session.getTransaction().commit();
		Front.addConnected();
		}
		else{
		session.getTransaction().commit();
		Front.addFullSlots();
		}
	}
	
}