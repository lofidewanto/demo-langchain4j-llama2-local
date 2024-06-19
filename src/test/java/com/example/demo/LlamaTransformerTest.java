package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LlamaTransformerTest {

	@Autowired
	LlamaTransformer llamaTransformer;

	@Test
	void test_transform_some_data() {
		// Llama / Ollama must be on
		llamaTransformer.transformCsvFileToOneLiner();
	}

}
