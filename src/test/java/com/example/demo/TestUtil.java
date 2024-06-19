package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TestUtil {

	private static final String TARGET_OUTPUT = "target/output-standalone.txt";

	public String checkResultFile() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(TARGET_OUTPUT));
		return lines.toString();
	}

	public void deleteFile() throws IOException {
		if (Files.exists(Paths.get(TARGET_OUTPUT))) {
			Files.delete(Paths.get(TARGET_OUTPUT));
		}
	}

}
