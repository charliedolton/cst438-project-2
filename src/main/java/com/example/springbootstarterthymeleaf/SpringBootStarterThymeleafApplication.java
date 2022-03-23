package com.example.springbootstarterthymeleaf;

import com.example.springbootstarterthymeleaf.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Controller
@SpringBootApplication
public class SpringBootStarterThymeleafApplication {
	public static String BASE_URI = "https://localhost:8080/";

	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	@Autowired
	private WishlistRepository wishlistRepository;
	@Autowired
	private ItemRepository itemRepository;


	@RequestMapping("/")
	public String landingPage() {
		return "landingPage";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}

	@RequestMapping("/about")
	public String about() {
		return "about";
	}

	@PostMapping("/signup") // post method for signUp
	public String addUser(@RequestParam String username,
										@RequestParam String fName,
										@RequestParam String lName,
										@RequestParam String password) {// parameters that this post method expect

		User user = new User(username,fName,lName,"",password);

		if(userRepository.findUserByUsername(username) == null){
			userRepository.save(user);
			return "redirect:/login";
		}

		return "redirect:/signup";
	}

	@RequestMapping("/login")
	public String login() {

		return "login";
	}
	@RequestMapping("/getLogedInUser")
	public User getLoggedInUser(HttpSession session) {

		User user = userRepository.findUserByUsername(((List<String>)session.getAttribute("sessionVar")).get(0));
		//System.out.println(user.getUsername() + "gfoisdo");
		return user;
	}
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		clearSessionVariables(session);
		return "login";
	}
	@RequestMapping("/homePage")
	public String landingPage(Model model, HttpSession session) {
		String uri = "https://localhost:8080/HomePage";
		//RestTemplate restTemplate = new RestTemplate();
		//User user = restTemplate.getForObject(uri, User.class);
		User user = null;

		if(isAuthenticated(session)){
			user = userRepository.findUserByUsername(((List<String>)session.getAttribute("sessionVar")).get(0));
			model.addAttribute("user", user);
			return "HomePage";
		}
		else{
			return "redirect:/login";
		}
	}

	//home screen route
	@RequestMapping("/home")
	public String home(@RequestParam Integer userId, Model model) {
		User user = userRepository.findUserByUserId(userId);
		model.addAttribute("user", user);
		return "home";
	}

	//route for creating wishlists
	@RequestMapping("/createWishlist")
	public String createWishlist(Model model, HttpSession session) {
		User user = null;

		if(isAuthenticated(session)){
			user = userRepository.findUserByUsername(((List<String>)session.getAttribute("sessionVar")).get(0));
			model.addAttribute("user", user);
			return "createWishlist";
		} else {
			return "login";
		}
	}

	// example of post method being used for logging in
	@PostMapping("/login")
	public String attemptLogin(@ModelAttribute("user") User user, HttpServletRequest sessionLink) {
		User user1 = userRepository.findUserByUsername(user.getUsername());
		List<String> sessionVar = new ArrayList<>();// this holds the session variables
		if (user1 == null) {
			return "User does not exist";
		}

		if(user1.getPassword().equals(user.getPassword())) {
			sessionVar.add(user.getUsername()); // we add a variable
			sessionVar.add("isAuthenticated"); // we add another variable
			sessionLink.getSession().setAttribute("sessionVar", sessionVar);// we save the list to the session link
			return "redirect:/homePage";
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
//	@GetMapping(path="api/userIsTaken")
//	public @ResponseBody boolean checkForUser(String username) {//checks for user by username
//		User user1 = userRepository.findByUsername(username);
//		Boolean returnVal;
//		if(user1 == null){
//			returnVal = false;
//		}
//		else{
//			returnVal = true;
//		}
//		return returnVal;
//	}
	//page for editing wishlists
	@RequestMapping("/editWishlist") // changed to view wishlist
	public String editWishlist(@RequestParam(defaultValue = "0") Integer wishlistId,
							   Model model, HttpSession session){
		WishList wishlist = wishlistRepository.findWishListByWishListId(wishlistId);
		model.addAttribute("wishlist", wishlist);

		if(isAuthenticated(session)){
			model.addAttribute("isAuthenticated", true);
		}
		else{
			model.addAttribute("isAuthenticated", false);
		}

		return "editWishlist";
	}

	//page for adding items to a wishlist
	@RequestMapping("/addItemToWishlist")
	public String addItemToWishlist(@RequestParam Integer wishlistId, Model model){
		WishList wishList = wishlistRepository.findWishListByWishListId(wishlistId);
		model.addAttribute("wishlist", wishList);
		return "addItemToWishlist";
	}

	@RequestMapping("/viewWishlist")
	public String viewWishlist(@RequestParam Integer wishlistId, Model model) {
		WishList wishlist = wishlistRepository.findWishListByWishListId(wishlistId);
		model.addAttribute("wishlist", wishlist);
		return "wishList";
	}




	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterThymeleafApplication.class, args);
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	public boolean isAuthenticated(HttpSession session){
		boolean returnVal = false;
		if(session.getAttribute("sessionVar") != null){
			if ( ((List<String>)session.getAttribute("sessionVar")).get(1)!= null){
				returnVal = true;
			}
		}
		return returnVal;
	}

	public void clearSessionVariables(HttpSession session){
		session.invalidate();
	}

}
