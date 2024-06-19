package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ManualTransformer {

	private static final Logger logger = LoggerFactory.getLogger(ManualTransformer.class);

	private static final String COL_SPLITTER = ";";

	public void transformCsvFileToOneLiner(String csvFilePath, String outputFilePath) {
		List<String> emails = new ArrayList<>();

		try {
			List<String> lines = Files.readAllLines(Paths.get(csvFilePath));
			if (!lines.isEmpty()) {
				String[] headers = lines.get(0).split(COL_SPLITTER);
				int emailIndex = -1;

				// Find the index of the "email" column
				for (int i = 0; i < headers.length; i++) {
					if (headers[i].equalsIgnoreCase("email")) {
						emailIndex = i;
						break;
					}
				}

				if (emailIndex == -1) {
					logger.info("No 'email' column found in the CSV file.");
					return;
				}

				// Read emails from each row
				for (int i = 1; i < lines.size(); i++) {
					String[] values = lines.get(i).split(COL_SPLITTER);
					if (values.length > emailIndex) {
						emails.add(values[emailIndex].trim());
					}
				}

				// Concatenate emails into one liner string
				String emailString = String.join(COL_SPLITTER, emails);

				logger.info("Result: " + emailString);

				// Check if the output file exists and append or create the file
				if (Files.exists(Paths.get(outputFilePath))) {
					Files.write(Paths.get(outputFilePath), (COL_SPLITTER + emailString).getBytes(),
							StandardOpenOption.APPEND);
				} else {
					Files.write(Paths.get(outputFilePath), emailString.getBytes(), StandardOpenOption.CREATE);
				}
				logger.info("Emails have been written to " + outputFilePath);
			} else {
				logger.info("The CSV file is empty.");
			}
		} catch (IOException e) {
			logger.error("Error: " + e.getMessage());
		}
	}

}
