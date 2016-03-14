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
	 * Run : java -jar application.jar -Dtessaract.datapath=/path/to/tessdata
	 */
}
