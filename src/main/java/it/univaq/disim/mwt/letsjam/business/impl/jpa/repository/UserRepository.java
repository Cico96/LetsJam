package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.User;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findUserById(Long id);
	
	User findUserByUsername(String username);

	User findUserByEmail(String email);
	
	boolean existsUserByUsername(String username);
	
	boolean existsUserByEmail(String email);
	
	void deleteUserById(Long id);

	@Modifying
	@Query(value = "UPDATE User ut SET ut.role = :amministratore WHERE ut.id = :id")
	void promoteToAdmin(@Param("id") Long id, @Param("amministratore") String amministratore);

//	void updateUser(User userr){
//		EntityManager em = emf.createEntityManager();
//		System.out.println("OPENING entityTransaction");
//		em.getTransaction().begin();
//		User user = em.find(User.class, userr.getId());
//		em.detach(user);
//// necessario un merge per scrivere le modifiche, dopo il detach
//		em.merge(userr);
//		em.getTransaction().commit();
//		em.close();
//	}

}
