package com.example.demo;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatMemory = "chatMemory", chatModel = "chatLanguageModel")
interface ChatAssistant {

	@SystemMessage("Please say I don't know, if you don't know the answer. Don't hallucinate. Only show the result.")
	String chat(String userMessage);

}
