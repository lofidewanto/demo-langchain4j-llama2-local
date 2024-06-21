package com.example.demo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

@Component
public class LlamaWithEasyRagTransformer {

	private static final Logger logger = LoggerFactory.getLogger(LlamaWithEasyRagTransformer.class);

	@Autowired
	private ChatLanguageModel ragLanguageModel;

	@Autowired
	private ChatMemory ragMemory;

	private ContentRetriever createContentRetriever(List<Document> documents) {
		InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

		logger.info("Ingest the doc starts...");
		EmbeddingStoreIngestor.ingest(documents, embeddingStore);
		logger.info("Ingest the doc ends...");

		return EmbeddingStoreContentRetriever.from(embeddingStore);
	}

	public void transform(String filePath) {
		Path path = Paths.get(filePath);

		Document document = FileSystemDocumentLoader.loadDocument(path, new ApacheTikaDocumentParser());
		List<Document> documents = new ArrayList<>();
		documents.add(document);

		ContentRetriever contentRetriever = createContentRetriever(documents);

		RagAssistant ragAssistant = AiServices.builder(RagAssistant.class).chatLanguageModel(ragLanguageModel)
				.chatMemory(ragMemory).contentRetriever(contentRetriever).build();

		createQuestions(ragAssistant);
	}

	private void createQuestions(RagAssistant ragAssistant) {
		String question1 = "Was ist der Inhalt des Dokuments? ";
		Result<String> answer = ragAssistant.chat(question1);
		logger.info("Answer of: " + question1 + answer.content());

		String question2 = "Wieviele Seiten hat das Dokument? ";
		answer = ragAssistant.chat(question2);
		logger.info("Answer of: " + question2 + answer.content());

		String question3 = "Welchen Geltungsbereich hat das Dokument? ";
		answer = ragAssistant.chat(question3);
		logger.info("Answer of: " + question3 + answer.content());

		String question4 = "Was ist der Inhalt des Artikels 43? ";
		answer = ragAssistant.chat(question4);
		logger.info("Answer of: " + question4 + answer.content());

		String question5 = "Was sind die Überwachungsgebühren? ";
		answer = ragAssistant.chat(question5);
		logger.info("Answer of: " + question5 + answer.content());

		String question6 = "Was ist der Inhalt des folgenden Bereiches: Zusammenarbeit mit den durch die Richtlinie (EU) 2022/2555 geschaffenen Strukturen und Behörden. ";
		answer = ragAssistant.chat(question6);
		logger.info("Answer of: " + question6 + answer.content());

		String question7 = "Bitte fass das Dokument übersichtlich - mit Aufzählung - zusammen! ";
		answer = ragAssistant.chat(question7);
		logger.info("Answer of: " + question7 + answer.content());

		String question8 = "Welche Richtlinie beinhaltet das Dokument? ";
		answer = ragAssistant.chat(question8);
		logger.info("Answer of: " + question8 + answer.content());

		String question9 = "Welche Verordnung beinhaltet das Dokument? ";
		answer = ragAssistant.chat(question9);
		logger.info("Answer of: " + question9 + answer.content());

		String question10 = "Von wann ist die Verordnung? ";
		answer = ragAssistant.chat(question10);
		logger.info("Answer of: " + question10 + answer.content());
	}

}
