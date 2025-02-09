package com.mroueh;


import com.mroueh.config.FakeDataGenerator;
import com.mroueh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceMultivendorApplication {

	@Autowired
	private FakeDataGenerator fakeDataGenerator;
	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceMultivendorApplication.class, args);
		System.out.println("successfully connected");
	}

//   @Bean
//    public CommandLineRunner seedDatabase() {
//		return args -> {
//			for (int i = 0; i < 10; i++) { // Generate 10 products
//				productService.addProductFaker(fakeDataGenerator.generateProductDto());
//			}
//		};
//	}
}
