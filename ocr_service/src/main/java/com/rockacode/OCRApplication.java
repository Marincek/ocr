package com.rockacode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OCRApplication {

	public static void main(String[] args) {
		TesseractProcessor.DATAPATH = "./src/main/resources/tessdata";
		
		SpringApplication.run(OCRApplication.class, args);
	}
}
