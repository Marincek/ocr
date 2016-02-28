package com.rockacode;

import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageProcessorApplication {

	public static void main(String[] args) {
		// java -Djava.library.path="..\opencv\build\java\x64" -jar
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		SpringApplication.run(ImageProcessorApplication.class, args);
	}
}
