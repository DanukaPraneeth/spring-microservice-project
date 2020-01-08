package com.myapps.microservice;

import javax.servlet.Servlet;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpListnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpListnerServiceApplication.class, args);
	}

}
