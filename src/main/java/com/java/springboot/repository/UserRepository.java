package com.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.springboot.model.User;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 7, 2017
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	public void deleteByUsername(String username);
}
