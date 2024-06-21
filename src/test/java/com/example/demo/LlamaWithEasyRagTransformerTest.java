package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LlamaWithEasyRagTransformerTest {

	@Autowired
	LlamaWithEasyRagTransformer llamaWithRagTransformer;

	@Test
	void test_transform() {
		String filePath = "src/main/resources/dorafiles/dora-de.pdf";

		llamaWithRagTransformer.transform(filePath);
	}

}
