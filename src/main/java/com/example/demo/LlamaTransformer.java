package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LlamaTransformer {

	private static final Logger logger = LoggerFactory.getLogger(LlamaTransformer.class);

	private final static String CHAT = """
			Please provide the concatenated email addresses in
			a single line, separated by semicolons, from the following input:
			""";

	private ChatAssistant chatAssistant;

	public LlamaTransformer(ChatAssistant chatAssistant) {
		this.chatAssistant = chatAssistant;
	}

	public void transformCsvFileToOneLiner(String csvFilePath, String outputFilePath) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(csvFilePath));

			String result = chatAssistant.chat(CHAT + lines.toString());

			logger.info("Result: " + result);

			if (Files.exists(Paths.get(outputFilePath))) {
				Files.write(Paths.get(outputFilePath), result.getBytes(), StandardOpenOption.APPEND);
			} else {
				Files.write(Paths.get(outputFilePath), result.getBytes(), StandardOpenOption.CREATE);
			}
			logger.info("Emails have been written to " + outputFilePath);
		} catch (IOException e) {
			logger.error("Error: " + e.getMessage());
		}
	}

}
