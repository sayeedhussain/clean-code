package com.demo.cleancode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
public class Service {


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
        ArrayList intArrayList = new ArrayList(List.of(-10, 11, -12, 8));
//		printSortedArray(intArrayList);
//		log.info(sorted.toString());
//		System.out.print(intArrayList);
//		System.out.print(sorted);
		createUser()
        calculate(10, 15, Operation.ADD);
    }
    public void createUser(String username, String email) {

        String usernameTrim = username.trim();
        if (usernameTrim.length() <= 6) {
            log.error("username must be more than 6 chars");
            return;
        }

        String alphaNumericRegexPattern = "^[a-zA-Z0-9]*$";
        boolean isUsernameValid = Pattern.compile(alphaNumericRegexPattern).matcher(username).matches();
        if (!isUsernameValid) {
            log.error("username must be alphaNumeric");
            return;
        }

        String emailRegexPattern = "^(.+)@(\\S+)$";
        boolean isEmailValid = Pattern.compile(emailRegexPattern).matcher(email).matches();
        if (!isEmailValid) {
            log.error("email is invalid");
            return;
        }

        if (repository.exists(username)) {
            log.error("user already exists");
            return;
        }

        User user = new User(username, email);
        repository.save(user);
    }

    public enum Operation {
        ADD,
        SUBTRACT
    }

    public int calculate(Integer a, Integer b, Operation op) {
        if (op == Operation.ADD) {
            return a + b;
        } else {
            return a - b;
        }
    }

//    Integer result = calculate(10, 15, Operation.SUBTRACT);
    //Is result -5 or 5? It's ambiguous.
    //Can't tell without looking into method body

    public int subtractFirstFromSecond(Integer first, Integer second) {
        return second - first;
    }

    Integer result = subtractFirstFromSecond(15, 10);
    //result = 5
}
