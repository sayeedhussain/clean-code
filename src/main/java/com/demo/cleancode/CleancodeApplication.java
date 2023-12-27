package com.demo.cleancode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
	private boolean isUserDetailsValid(String username, String email) {
		if (!isUsernameValid(username)) {
			return false;
		}
		if (!isEmailValid(email)) {
			return false;
		}
		return true;
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
	}

	public void addIdForUser(User user) {
		user.id = UUID.randomUUID();
	}
}
