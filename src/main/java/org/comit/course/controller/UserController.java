package org.comit.course.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.comit.course.bean.User;
import org.comit.course.service.UserService;
import org.comit.course.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	///// CREATE INDEX
	
	@GetMapping({"/", "/index"})
	public String index() {
		System.out.println("Show index page");
		
		return "index";
	}
	
	//// CREATE LIST
	@GetMapping("/list")
	public ModelAndView listUsers() {
		System.out.println("List Users");
		
		List<User> users = this.userService.listUsers();
		
		return new ModelAndView("list", "users", users);
	}
	
	////CREATE 
	
	@GetMapping("/create")
	public String showCreate() {
		System.out.println("Show Create");
		return "create";
	}
	
	////CREATE new user
	
	@PostMapping ("/create")
	public String createUser(HttpServletRequest request) {
		System.out.println("Show Create");
		
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String birth = request.getParameter("birth");
		
		User user = this.createUser(null, first, last, username, password, birth);
		this.userService.createUser(user);
		
		return "redirect:/list";
	}
	

	
	///UPDATE
	@GetMapping("/update/{id}")
	public ModelAndView showUpdate(@PathVariable("id") int id) {
		System.out.println("Show Update");
		
		User user = this.userService.findUser(id);
		
		return new ModelAndView("update", "user", user);
	}
	
	@PostMapping("/update")
	public String updateUser(HttpServletRequest request) {
		System.out.println("Update User");

		String id = request.getParameter("id");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String birth = request.getParameter("birth");

		User user = this.createUser(id,first, last, username, password, birth);

		this.userService.modifyUser(user);

		return "redirect:/list";
	}
	
	//DELETE USER
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		System.out.println("Delete User");
		this.userService.deleteUser(id);
		
		return "redirect:/list";
	}
	
	//create a method for collecting all strings in one bin
	
	private User createUser(String id, String first, String last, String username, String password, String birth) {
		User user = new User(Util.parseId(id), first.trim(), last.trim(), username.trim(), password, Util.parseDate(birth), "");
		
		return user;
	}
	
}
