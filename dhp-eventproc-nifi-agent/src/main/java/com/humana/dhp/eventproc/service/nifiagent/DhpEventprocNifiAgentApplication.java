package com.humana.dhp.eventproc.service.nifiagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DhpEventprocNifiAgentApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(DhpEventprocNifiAgentApplication.class, args);
	}

}
