package org.comit.course.service;

import java.util.List;

import org.comit.course.bean.User;
import org.comit.course.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	///////////// LIST USERS METHOD ///////////////////////
	

	public List<User> listUsers(){
		
		List<User> users = userDao.listUsers();
		
		users.removeIf(user-> user.getStatus()!= "A");
		
		return users;
	}
	
	/////////////// FIND USERS METHOD /////////////////////
	
	public User findUser(int idUser) {
		
		return userDao.findUser(idUser);
	}
	
	/////////////// CREATE USERS METHOD /////////////////////
	
	public void createUser (User user) {
		this.validateUser(user);
		userDao.createUser(user);
	}

	/////////////// MODIFY USERS METHOD (FOR SYNCRONISED) /////////////////////
	
	public void modifyUser(User user) {
		this.validateUser(user);
		userDao.modifyUser(user);
	}
	
	/////////////// DELETE USERS METHOD /////////////////////
	
	public void deleteUser(int idUser) {
		userDao.deleteUser(idUser);
	}
	
	////////VALIDATION OF USER /////////
	
	private void validateUser(User user) {
		if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getUsername().isEmpty()) {
	    	throw new RuntimeException("Invalid User Data " + user);
		}
	}
	
	
	
	
	
}
