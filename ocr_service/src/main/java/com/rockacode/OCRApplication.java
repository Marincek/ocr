package com.rockacode;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OCRApplication {

	static {
		System.setProperty("jna.library.path", "src/main/resources/dlls");
	}

	public static void main(String[] args) throws IOException {

		SpringApplication.run(OCRApplication.class, args);

	}

	/**
	 * Run the app with command : java -jar ocrprocessor-0.0.1-SNAPSHOT.jar
	 * -Dtessaract.datapath=/path/to/tessdata
	 */
}
