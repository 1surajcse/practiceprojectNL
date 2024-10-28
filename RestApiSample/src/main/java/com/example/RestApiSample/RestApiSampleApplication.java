package com.example.RestApiSample;


import com.example.RestApiSample.book.Book;
import com.example.RestApiSample.book.BookRepository;
import com.example.RestApiSample.user.User;
import com.example.RestApiSample.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class RestApiSampleApplication {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;
	@PostConstruct
	public void saveBook(){
		bookRepository.save(new Book(111,"Java"));
				bookRepository.save(new Book(222,"DataBase"));
	}
	@PostConstruct
	public void saveUser(){
		userRepository.save(new User(1,"Ram"));
		userRepository.save(new User(2,"Mohan"));
	}
	@GetMapping("/books")
	public List<Book>books(){
		return bookRepository.findAll();
	}
	@GetMapping("/users")
	public List<User>users(){
		return userRepository.findAll();
	}



	public static void main(String[] args) {

		ApplicationContext applicationContext=SpringApplication.run(RestApiSampleApplication.class, args);




		TransportType transportType=applicationContext.getBean("transportType",TransportType.class);
		System.out.println("c1 "+transportType);
		TransportType transportType1=applicationContext.getBean("transportType",TransportType.class);
		System.out.println("c2 "+transportType1);
		transportType.type();
	}

}
