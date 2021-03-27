package repository;

import entity.User;
import querries.UserQuerries;

import javax.persistence.*;
import java.util.List;

public class UserRepo {
	
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("city_hall");
	
	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void updateUser(User user){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(User user){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(user) ? user : em.merge(user));
		em.getTransaction().commit();
		em.close();
	}

	public User findByEmail(String email){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery(UserQuerries.FIND_USER_BY_EMAIL);
		query.setParameter("user_email",email);
		if(query.getResultList().size()==0){
			return null;
		}
		User user=(User) query.getResultList().get(0);
		em.close();
		return user;
	}

	public User findByEmailAndPassword(String email,String password){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery(UserQuerries.FIND_USER_BY_EMAIL_AND_PASSWORD);
		query.setParameter("user_email",email);
		query.setParameter("user_password",password);
		if(query.getResultList().size()==0){
			return null;
		}
		User user=(User) query.getResultList().get(0);
		em.close();
		return user;
	}

	public User findById(String id){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery(UserQuerries.FIND_USER_BY_ID);
		query.setParameter("user_id",id);
		if(query.getResultList().size()==0){
			return null;
		}
		User user=(User) query.getResultList().get(0);
		em.close();
		return user;
	}

	public List<User> findAllUsers(){
		EntityManager em=entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<User> query=em.createQuery(UserQuerries.FIND_ALL_USERS,User.class);
		List<User> users= query.getResultList();
		em.close();
		return users;
	}

}
