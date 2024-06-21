package com.example.demo;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;

public interface RagAssistant {

	@SystemMessage("Antwort stets auf Deutsch. Wenn du die Antwort nicht kennst, bitte keine Hallusination, beantworte mit: Ich weiß leider nicht. ")
	Result<String> chat(String userMessage);

}
