package org.comit.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.comit.course.bean.User;
import org.comit.course.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	///////////// LIST USERS METHOD ///////////////////////
	
	public List<User> users; //created properties
	
	UserDao(){ //created construction
		this.users = new ArrayList<>();
		
		this.users.add(new User(1,"Fredrick","Krumm","FKRUMM","123",Util.parseDate("1992-02-14"),"A"));
		this.users.add(new User(2,"Jaymie","Dunning","JDUNNING","123",Util.parseDate("1994-08-04"),"A"));
		this.users.add(new User(3,"Kristy","Rayford","KRAYFORD","123",Util.parseDate("1995-04-12"),"A"));
		this.users.add(new User(4,"Faye","Redington","FREDINGTON","123",Util.parseDate("1996-12-08"),"A"));
		this.users.add(new User(5,"Bobbye","Stinebaugh","BSTINEBAUGH","123",Util.parseDate("2000-02-05"),"A"));

	}
	
	public List<User> listUsers(){
		return users;
	}
	
	/////////////// FIND USERS METHOD /////////////////////
	
	public User findUser(int idUser) {
		
		User user = new User();
		user.setIdUser(idUser);
		
		int index = this.users.indexOf(user);
		
		if (index >= 0) {
			user = this.users.get(index);
		}
		return user;
	}
	
	/////////////// CREATE USERS METHOD /////////////////////
	
	public synchronized void createUser (User user) {
		 
		//create automat id
		
		int max = 0;
		for (User u: users) { //looping over whole list of id for looking maximum
			if (u.getIdUser()>max) {
				max=u.getIdUser();
			}
		}
		
		user.setIdUser(++max); //assign a new value through increment
		user.setStatus("A");
		
		this.users.add(user); //add the user into the list	
	}
	
	/////////////// MODIFY USERS METHOD (FOR SYNCRONISED) /////////////////////
	
	public void modifyUser(User user) {
		
		//first we need to find user
		User u = this.findUser(user.getIdUser());
		
		if (u != null) {
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setUsername(user.getUsername());
			u.setBirth(user.getBirth());
		}
	}
	
	/////////////// DELETE USERS METHOD /////////////////////
	
	public void deleteUser(int idUser) {
		User user = new User();
		user.setIdUser(idUser);
		
		int index = this.users.indexOf(user);
		this.users.remove(index);
		
		//this.users.removeIf(u-> u.getIdUser()==idUser); //functional programming, instead of four lines up
	}
	
	
	
}
