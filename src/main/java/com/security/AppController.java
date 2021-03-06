package com.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	@Autowired
	private UserRepository repo;
	
	@GetMapping("")
	public String viewHomePage()
	{
		return "Index1";
	}
	
	@GetMapping("/Contact.html")
	public String showContactForm()
	{
		return "Contact";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model)
	{
		model.addAttribute("user", new User());
		
		return"signup_form";
	}
	
	@GetMapping("/Login1.html")
	public String showLoginForm()
	{
		return "Login1";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		return "register_success";
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model model)
	{
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

}
