package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ManualTransformerTest {

	@Test
	void test_transform_some_files() throws IOException {
		TestUtil testUtil = new TestUtil();
		testUtil.deleteFile();

		String pathInput = "src/test/resources/testfiles/";
		String pathOutput = "target/";

		ManualTransformer manualTransformer = new ManualTransformer();
		manualTransformer.transformCsvFileToOneLiner(pathInput + "email-test-file1.csv.txt",
				pathOutput + "output-standalone.txt");

		assertEquals("[test@test.de;max@test.de;min@test.de]", testUtil.checkResultFile());
	}

}
