package ru.chelogaev.dm.measurementrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;


@SpringBootApplication
public class MeasurementRestApplication {
	static {
		Locale.setDefault(Locale.ENGLISH);
	}

	public static void main(String[] args) {
		SpringApplication.run(ru.chelogaev.dm.measurementrestapp.MeasurementRestApplication.class, args);
	}

}
