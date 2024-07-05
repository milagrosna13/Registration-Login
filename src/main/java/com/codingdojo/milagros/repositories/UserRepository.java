package com.codingdojo.milagros.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.milagros.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	
	//CONSULTA QUE ME REGRESE UN USER EN BASE A SU EMAIL
	User findByEmail(String email);

	
	
	
}
