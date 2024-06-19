package com.example.demo;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface Assistant {

	@SystemMessage("You are a polite assistant. Please say I don't know, if you don't know the answer. Don't hallucinate.")
	String chat(String userMessage);
}
