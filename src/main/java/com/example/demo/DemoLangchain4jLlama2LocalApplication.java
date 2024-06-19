package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

@SpringBootApplication
public class DemoLangchain4jLlama2LocalApplication implements CommandLineRunner {

	private static final String OLLAMA_HOST = "http://localhost:11434";

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

	@Bean
	ChatMemory chatMemory() {
		return MessageWindowChatMemory.withMaxMessages(10);
	}

	@Bean
	ChatLanguageModel chatLanguageModel() {
		ChatLanguageModel model = OllamaChatModel.builder().baseUrl(OLLAMA_HOST).modelName("llama3").build();
		return model;
	}

}
