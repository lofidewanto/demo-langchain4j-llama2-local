package com.example.demo;

import org.junit.jupiter.api.Test;

class ManualTransformerTest {

	@Test
	void test_transform_some_files() {
		String pathInput = "src/test/resources/testfiles/";
		String pathOutput = "target/";

		ManualTransformer manualTransformer = new ManualTransformer();
		manualTransformer.transformCsvFileToOneLiner(pathInput + "email-test-file1.csv.txt",
				pathOutput + "output-standalone.txt");
	}

}
