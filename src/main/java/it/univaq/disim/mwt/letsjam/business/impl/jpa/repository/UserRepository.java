package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findUserById(Long id);
	
	User findUserByUsername(String username);

	User findUserByEmail(String email);
	
	boolean existsUserByUsername(String username);
	
	boolean existsUserByEmail(String email);
	
	void deleteUserById(Long id);

}
