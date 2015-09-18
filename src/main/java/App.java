import java.sql.SQLException;

import javax.swing.JFrame;

import entities.Player;

import org.hibernate.Session;
import org.postgresql.util.PSQLException;

public class App {
	public static String correctinput;

	public static void main(String[] args) {
		Frontend frontend = new Frontend();
		frontend.setTitle("MMORG Database Management System");
		frontend.setSize(600, 400);
		frontend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frontend.setVisible(true);
	}

	public static void login() {

		Session sessionA = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		try {
			sessionA.beginTransaction();
			Player result = (Player) HibernateUtil
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"from Player where username = ? and password = ?")
					.setString(0, Frontend.getUsernameInput())
					.setString(1, Frontend.getPasswInput()).list().get(0);

			System.out.println("Uitvoer Scenario 1:" + result);
			sessionA.getTransaction().commit();
		} catch (IndexOutOfBoundsException e) {
			Frontend.addIncorrectMessage();
			sessionA.getTransaction().commit();
		}
	}

	public static void registerUser() {
		Session sessionB = HibernateUtil.getSessionFactory()
				.getCurrentSession();
	//	try {
			sessionB.beginTransaction();
			Player player = new Player();
			player.setUsername(Frontend.getUsernameInput());
			player.setPassword(Frontend.getPasswInput());
			sessionB.save(player);
			sessionB.getTransaction().commit();
	//	} catch (SQLException e) {
	//		final String ss = e.getSQLState();
			//Frontend.addUsedMessage();
			//sessionB.getTransaction().commit();
	//	}

	}
}