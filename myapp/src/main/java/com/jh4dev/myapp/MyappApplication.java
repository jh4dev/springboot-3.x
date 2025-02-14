package com.jh4dev.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {

		ApplicationContext ac =  SpringApplication.run(MyappApplication.class, args);
		String[] beans = ac.getBeanDefinitionNames();

//		for(String b : beans) {
//			System.out.println(b);
//		}
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);

		return sessionLocaleResolver;
	}

}
