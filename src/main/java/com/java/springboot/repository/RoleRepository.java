package com.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.springboot.model.Role;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 18, 2017
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	public void deleteByName(String name);
}
