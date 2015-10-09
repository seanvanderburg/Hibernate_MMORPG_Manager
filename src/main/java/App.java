import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.postgresql.util.PSQLException;

import entities.Player;

//database related operations
public class App {

	public static void login() {

		Session sessionA = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();

		sessionA.beginTransaction();
		Front.removeMessage();
		List result = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Player where username = ? OR password = ?")
				.setString(0, Front.getUsernameInput())
				.setString(1, Front.getPasswInput()).list();
		
		if (result.isEmpty() || result == null) {
			System.out.println(result);
			Front.addIncorrectMessage();
			sessionA.getTransaction().commit();
		} else {
			System.out.println("Uitvoer inloggen:" + result);
			sessionA.getTransaction().commit();
			Front.toggleMenu();

		}
	}
	

	public static void registerUser() throws PSQLException {
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
			player.setBalance(0);
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
		
		
		
		Session session = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		String username = Front.username;
		Criteria criteria = session.createCriteria(Player.class);
		Player player = (Player) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		
		int currentslots = player.getCharacterslots(); 		
		entities.Character character = new entities.Character();
		character.setPlayer(player);
		character.setRace(Front.selectedRace);
		character.setCharClass(Front.selectedClass);
		character.setLevel(getRandom());
		player.setCharacterslots(currentslots - 1);
		session.save(player);
		session.save(character);
		System.out.println("Hoi " + player.getCharacters());
		session.getTransaction().commit();
		
	}
	
	public static void updatePayment(){
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
	
	public static int getRandom(){
		return (int)(Math.random()*100);
	}
}