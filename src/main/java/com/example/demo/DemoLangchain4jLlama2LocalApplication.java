package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoLangchain4jLlama2LocalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoLangchain4jLlama2LocalApplication.class, args);
	}

	@Autowired
	private ManualTransformer manualTransformer;

	@Override
	public void run(String... args) throws Exception {
		for (String arg : args) {
			String path = "target/";

			String inputCsvFile = arg;
			String outputTxtFile = path + "output.txt";

			manualTransformer.transformCsvFileToOneLiner(inputCsvFile, outputTxtFile);
		}
	}

}
