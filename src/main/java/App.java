import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import entities.Character;
import entities.Server;
import entities.Player;

import org.hibernate.Query;
import org.hibernate.Session;

public class App {
	public static String correctinput;

	public static void main(String[] args) {
		Frontend frontend = new Frontend();
		frontend.setTitle("MMORG Database Management System");
		frontend.setSize(1000, 200);
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
					.createQuery("from Player where username = ? and password = ?")
					.setString(0, Frontend.getUsername()).setString(1, Frontend.getPasswInput()).list()
					.get(0);

			System.out.println("Uitvoer Scenario 1:" + result);
			sessionA.getTransaction().commit();
		} catch (IndexOutOfBoundsException e) {
			Frontend.addIncorrectMessage();
		}
		finally{
			sessionA.getTransaction().commit();
		}
	}

	public static void registerUser() {
		Session sessionB = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		sessionB.beginTransaction();
		Player player = new Player();
		//player.setUsername(username);
		//player.setPassword(password);
		sessionB.save(player);
		sessionB.getTransaction().commit();

	}
}