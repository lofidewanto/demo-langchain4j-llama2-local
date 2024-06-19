package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LlamaTransformerTest {

	@Autowired
	LlamaTransformer llamaTransformer;

	@Autowired
	TestUtil testUtil;

	@Test
	void test_transform_some_data() throws IOException {
		testUtil.deleteFile();

		String pathInput = "src/test/resources/testfiles/";
		String pathOutput = "target/";

		// Llama / Ollama must be on
		llamaTransformer.transformCsvFileToOneLiner(pathInput + "email-test-file1.csv.txt",
				pathOutput + "output-standalone.txt");

		assertEquals("[test@test.de;max@test.de;min@test.de]", testUtil.checkResultFile());
	}

}
