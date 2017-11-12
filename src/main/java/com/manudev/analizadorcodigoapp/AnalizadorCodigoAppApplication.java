package com.manudev.analizadorcodigoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.manudev.analizadorcodigoapp"})
public class AnalizadorCodigoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalizadorCodigoAppApplication.class, args);
	}
}
