import java.util.List;

import org.hibernate.Session;
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
		Session sessionB = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		sessionB.beginTransaction();
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
			sessionB.save(player);
			Front.addConfirmMessage();
			sessionB.getTransaction().commit();
		} else {
			Front.addUsedMessage();
			sessionB.getTransaction().commit();
		}
	}
	
	public static void addMoney(){
		
		//TODO add money to user account. update record, insert balance... where user = username
	}
}