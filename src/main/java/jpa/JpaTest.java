package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			test.createHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		test.listHome();
		manager.close();
		factory.close();
		System.out.println(".. done");
	}

	private void createHome() {
		int num = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList().size();
		if (num == 0) {
			for (int i = 0; i < 3; i++) {
				ElectronicDevice electronicDevice = new ElectronicDevice();
				electronicDevice.setName("ElectronicDevice" + i);
				electronicDevice.setConsommation(50 + i);
				manager.persist(electronicDevice);
			}

			for (int i = 0; i < 3; i++) {
				Heater heat = new Heater();
				heat.setName("Heater" + i);
				manager.persist(heat);
			}

			for (int i = 0; i < 10; i++) {
				Person person = new Person("test" + i, "toto" + i, "test@test.fr" + i);
				manager.persist(person);
				Home home = new Home(70, 15, person);
				manager.persist(home);
			}

		}
	}

	private void listHome() {
		List<Home> resultList = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList();
		System.out.println("nombre de maisons: " + resultList.size());
		for (Home next : resultList) {
			System.out.println("maison de : " + next.getPerson().getFirstName());
		}
	}
}
