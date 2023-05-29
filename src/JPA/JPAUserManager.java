package JPA;

import java.util.List;




import javax.persistence.*;


import Interfaces.UserManager;
import POJO.*;


public class JPAUserManager implements UserManager{

	private EntityManager em;


	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("DentalClinic_Proyect-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		if (this.getRoles().isEmpty()) {
			Role dentist = new Role("dentist");
			Role receptionist = new Role("receptionist");
			Role suppliers = new Role("suppliers");
			Role client = new Role("client");
			this.createRole(dentist);
			this.createRole(receptionist);
			this.createRole(suppliers);
			this.createRole(client);
			
		}
	}

	public void close() {
		em.close();
	}

	@Override
	public void register(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	@Override
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();
	}

	@Override
	public void assignRole(User user, Role role) {
		em.getTransaction().begin();
		user.setRole(role);
		role.addUser(user);
		em.getTransaction().commit();
	}

	@Override
	public User login(String name, String password) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, name);
			q.setParameter(2, password);
			User user = (User) q.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = q.getResultList();
		return roles;
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r = (Role) q.getSingleResult();
		return r;
	}

	@Override
	public void deleteUser (String email) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email LIKE ?", User.class);
		q.setParameter(1, email);
		User u= (User) q.getSingleResult();
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}

	@Override
	public void changeEmail(String emailNew, String emailOld) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email LIKE ?", User.class);
		q.setParameter(1, emailOld);
		User u= (User) q.getSingleResult();
		em.getTransaction().begin();
		u.setEmail(emailNew);
		em.getTransaction().commit();
	}

	



}
