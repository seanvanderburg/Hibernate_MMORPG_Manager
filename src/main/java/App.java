import java.sql.SQLException;
import java.util.List;
import java.util.logging.ErrorManager;

import javax.swing.JFrame;

import entities.Player;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.postgresql.util.PSQLException;

public class App {
	public static String correctinput;

	public static void main(String[] args) {
		Frontend frontend = new Frontend();
		frontend.setTitle("MMORG Database Management");
		frontend.setSize(800, 600);
		frontend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frontend.setVisible(true);
	}

	public static void login() {

		Session sessionA = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();

		sessionA.beginTransaction();
		Frontend.removeMessage();
		List result = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Player where username = ? OR password = ?")
				.setString(0, Frontend.getUsernameInput())
				.setString(1, Frontend.getPasswInput()).list();

		if (result.isEmpty()) {
			Frontend.addIncorrectMessage();
			sessionA.getTransaction().commit();
		} else {
			System.out.println("Uitvoer inloggen:" + result);
			sessionA.getTransaction().commit();
		}
	}

	public static void registerUser() throws PSQLException {
		Session sessionB = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		sessionB.beginTransaction();
		Frontend.removeMessage();
		List checkresult = HibernateUtil.getSessionFactory()
				.getCurrentSession()
				.createQuery("from Player where username = ? OR password = ?")
				.setString(0, Frontend.getUsernameInput())
				.setString(1, Frontend.getPasswInput()).list();

		if (checkresult.isEmpty()) {
			Player player = new Player();
			player.setUsername(Frontend.getUsernameInput());
			player.setPassword(Frontend.getPasswInput());
			sessionB.save(player);
			Frontend.addConfirmMessage();
			sessionB.getTransaction().commit();
		} else {
			Frontend.addUsedMessage();
			sessionB.getTransaction().commit();
		}
	}
}