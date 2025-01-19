package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

@SpringBootApplication
public class DemoLangchain4jLlama2LocalApplication implements CommandLineRunner {

	public static final String MODEL_NAME = "llama3.2";

	public static final String OLLAMA_HOST = "http://localhost:11434";

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
		ChatLanguageModel model = OllamaChatModel.builder().baseUrl(OLLAMA_HOST).modelName(MODEL_NAME).build();
		return model;
	}

	@Bean
	EmbeddingModel embeddingModel() {
		OllamaEmbeddingModel model = OllamaEmbeddingModel.builder().baseUrl(OLLAMA_HOST).modelName(MODEL_NAME).build();
		return model;
	}

	@Bean
	ChatMemory ragMemory() {
		return MessageWindowChatMemory.withMaxMessages(10);
	}

	@Bean
	ChatLanguageModel ragLanguageModel() {
		ChatLanguageModel model = OllamaChatModel.builder().baseUrl(DemoLangchain4jLlama2LocalApplication.OLLAMA_HOST)
				.modelName(DemoLangchain4jLlama2LocalApplication.MODEL_NAME).build();
		return model;
	}

}
