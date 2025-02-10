package com.jh4dev.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {

		ApplicationContext ac =  SpringApplication.run(MyappApplication.class, args);
		String[] beans = ac.getBeanDefinitionNames();

//		for(String b : beans) {
//			System.out.println(b);
//		}
	}

}
