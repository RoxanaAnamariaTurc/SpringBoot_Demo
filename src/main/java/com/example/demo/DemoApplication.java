package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}
	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address("England","London","NW9");
			String email = "jenny@email.com";
			Student student = new Student(
					"Jenny","Smith",
					email,Gender.FEMALE,
					address, List.of("Computer Science"), BigDecimal.TEN, LocalDateTime.now());

			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> {
						System.out.println(s + "already exists");
					}, () -> {
						System.out.println("Inserting student" + student);
						repository.insert(student);
					});

//			Query query = new Query();
//			query.addCriteria(Criteria.where("email").is(email));
//
//			List<Student> students = mongoTemplate.find(query,Student.class);
//			if(students.size() > 1){
//				throw new IllegalStateException("found many students with email " + email);
//			}
//			if(students.isEmpty()){
//
//			}

		};
	}

}
