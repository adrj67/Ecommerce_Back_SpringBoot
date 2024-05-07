package com.Springecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// (exclude = DataSourceAutoConfiguration.class)Evita que se conecte a la base 
//de datos para que no de error en el desarrollo
@SpringBootApplication (exclude = DataSourceAutoConfiguration.class)
public class SpringEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEcommerceApplication.class, args);
	}

}
