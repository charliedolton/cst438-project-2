package com.example.springbootstarterthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SpringBootApplication
public class SpringBootStarterThymeleafApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Root";
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
