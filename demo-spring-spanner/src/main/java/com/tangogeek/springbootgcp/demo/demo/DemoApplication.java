package com.tangogeek.springbootgcp.demo.demo;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}

	@RestController
	@Slf4j
	class HelloController {
		private final PersonRepository personRepository;
		
		HelloController(PersonRepository personRepository){
			this.personRepository=personRepository;
		}		
		
		@GetMapping ("/hello/{name}")
		public String hello (@PathVariable String name) {
			log.debug("hello "+ name);
			Person p=new Person();
			p.setName(name);
			personRepository.save(p);
			return "Hello " + name;
		}
	}

@Data
@Table (name = "people")
class Person{
	@PrimaryKey
	String id=UUID.randomUUID().toString();
	String name;
}

@RepositoryRestResource	
interface PersonRepository extends PagingAndSortingRepository<Person, Key>{
	List<Person> findAllByName(String name);
	
}
