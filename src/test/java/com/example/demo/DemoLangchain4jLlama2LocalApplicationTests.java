package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoLangchain4jLlama2LocalApplicationTests {

	@Autowired
	DemoLangchain4jLlama2LocalApplication application;

	@Test
	void contextLoads() {
	}

	@Test
	void test_some_inputs() throws Exception {
		String path = "src/test/resources/testfiles/";

		application.run(path + "email-test-file1.csv.txt", path + "email-test-file2.csv.txt");
	}

}
