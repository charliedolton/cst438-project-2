package com.example.springbootstarterthymeleaf;

import com.example.springbootstarterthymeleaf.database.User;
import com.example.springbootstarterthymeleaf.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SpringBootApplication
public class SpringBootStarterThymeleafApplication {

	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;


	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup") // post method for signUp
	public @ResponseBody String addUser(@RequestParam String username,
										@RequestParam String fName,
										@RequestParam String lName,
										@RequestParam String password) {// parameters that this post method expect

		User user = new User(username,fName,lName,null,password);
		//userRepository.save(user);
		return "login";

	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// example of post method being used for logging in
	@PostMapping("/login")
	public @ResponseBody String attemptLogin(@ModelAttribute("user") User user) {
		User user1 = userRepository.findByUsername(user.getUsername());
		if (user1 == null) {
			return "User does not exist";
		}

		if(user1.getPassword().equals(user.getPassword())) {
			return "Password is valid";
		} else {
			return "Password is incorrect";
		}
	}

	// example of post method
	@PostMapping("/postbody")
	public @ResponseBody String postBody(@RequestBody String fullName) {
		return "Hello " + fullName;
	}

	// example of accessing the database
	// returns all users in the database
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	@GetMapping(path="api/userIsTaken")
	public @ResponseBody boolean checkForUser(String username) {//checks for user by username
		User user1 = userRepository.findByUsername(username);
		Boolean returnVal;
		if(user1 == null){
			returnVal = false;
		}
		else{
			returnVal = true;
		}
		return returnVal;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterThymeleafApplication.class, args);
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
