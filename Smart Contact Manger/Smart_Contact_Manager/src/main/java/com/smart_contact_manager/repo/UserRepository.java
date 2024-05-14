package com.smart_contact_manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smart_contact_manager.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("Select u from User u Where u.email = : email")
	public User getUserByUserName(@Param("email") String email);
}
