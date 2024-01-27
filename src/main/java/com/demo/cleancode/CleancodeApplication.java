package com.demo.cleancode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Pattern;

@SpringBootApplication
@Slf4j
public class CleancodeApplication {

	Repository repository;

	public class Repository {
		boolean exists(Object ob) {
			return true;
		}
		void save(Object ob) {

		}
	}

	public class User {
		private String username;
		private String email;

		public UUID id;

		public User(String username, String email) {
			this.username = username;
			this.email = email;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(CleancodeApplication.class, args);
	}
	public void createUser(String username, String email) {
		if (!isUserDetailsValid(username, email)) {
			return;
		}
		if (isUserExists(username)) {
			return;
		}
		User user = new User(username, email);
		saveUser(user);

		Account account = new Account();
		debitAccount(account, 100);
	}
	private boolean isUserDetailsValid(String username, String email) {
		if (!isUsernameValid(username)) {
			return false;
		}
		if (!isEmailValid(email)) {
			return false;
		}
		return true;

		//Same level of abstraction throughout.
	}
	private boolean isUsernameValid(String username) {
		String usernameTrim = username.trim();
		if (usernameTrim.length() <= 6) {
			log.error("username must be more than 6 chars");
			return false;
		}

		String alphaNumericRegexPattern = "^[a-zA-Z0-9]*$";
		boolean isUsernameValid = Pattern.compile(alphaNumericRegexPattern).matcher(username).matches();
		if (!isUsernameValid) {
			log.error("username must be alphaNumeric");
			return false;
		}
		return true;
	}
	private boolean isEmailValid(String email) {
		String emailRegexPattern = "^(.+)@(\\S+)$";
		boolean isEmailValid = Pattern.compile(emailRegexPattern).matcher(email).matches();
		if (!isEmailValid) {
			log.error("email is invalid");
			return false;
		}
		return true;
	}
	private boolean isUserExists(String username) {
		if (repository.exists(username)) {
			log.error("user already exists");
			return false;
		}
		return true;
	}
	private void saveUser(User user) {
		repository.save(user);
		addToCart(null);
		addToCart(null, 0);
		addItemToCart();
	}

	public void addIdForUser(User user) {
		user.id = UUID.randomUUID();
	}

	public class Point {
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public class CartItem {
		private String productCode;
		private int quantity;
		public CartItem(String productCode, int quantity) {

		}
	}

	public void addItemToCart() {
		String productCode = "PC_1";
		int quantity = 5;
		CartItem item = new CartItem(productCode, quantity);
		addToCart(item);
	}

	private void addToCart(CartItem item) {
		//<function body> ....
	}

	private void addToCart(String productCode, int quantity) {
		//<function body> ....
	}

	public void validateUser(User user) {
		//validate user.username
		//validate user.email
	}

	public void drawCircle(int x, int y, double radius) {
		//<function body> ....
	}

	public void drawCircle(Point center, double radius) {
		//<function body> ....
	}

	public void getContent(String userId) {
		//<function body> ....

		//Method name is too generic. What content?
	}

	public void getBlogPosts(String userId) {
		//<function body> ....

		//Method name is specific.
	}

	public List<User> handleResponse(HttpResponse response) {
		//<function body> ....
		return List.of();

		//Method name does not convey what exactly will happen.
		//Will the response also get persisted?
		//Need to look at the body.
	}

	public List<User> getUsersFromResponse(HttpResponse response) {
		//<function body> ....
		return List.of();

		//Method name conveys clearly that users will be parsed from response.
		//Additionally return type aligns well with method name.
	}

	public class Account {
		public boolean isActive;
		public boolean is2FactorEnabled;
		public double balance;

		public Account() {
		}
		public boolean canDebitAmount(double amount) {
			return balance >= amount;
		}

		public void debit(double amount) {
			balance -= amount;
		}
	}

	public void debitAccount(Account account, double amount) {
		if (!account.isActive) {
			log.error("account is inactive");
			return;
		}
		if (!account.is2FactorEnabled) {
			log.error("2nd factor not enabled for account");
			return;
		}
		if (!account.canDebitAmount(amount)) {
			log.error("low balance in account");
			return;
		}
		//Much better Readability.
		//Avoid nesting through logic inversion and early returns in if conditions
	}

//	public List<String> getUsers() {
//		//<function body> ....
//		return List.of("userId1", "userId2");
//		//Method name and return type is not aligned.
//		//Name conveys List<Users> will be returned
//	}

	public List<User> getUsers() {
		//<function body> ....
		return List.of();
	}

}
