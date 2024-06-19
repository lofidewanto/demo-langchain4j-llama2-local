package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LlamaTransformer {

	private static final Logger logger = LoggerFactory.getLogger(LlamaTransformer.class);

	private final static String CHAT = """
			Please provide the concatenated email addresses in
			a single line, separated by semicolons, from the following input:
			""";

	private final static String DATA = """
			Name;Email
			Angel;angel@test.de
			Hero;hero@test.de
			Superman;superman@test.de
			""";

	@Autowired
	private Assistant assistant;

	public void transformCsvFileToOneLiner() {
		String result = assistant.chat(CHAT + DATA);

		logger.info(result);
	}

}
