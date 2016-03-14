package com.rockacode;

import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageProcessorApplication {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {

		SpringApplication.run(ImageProcessorApplication.class, args);

	}
	
	/**
	 * Run : java -jar application.jar -Djava.library.path="..\opencv\build\java\x64"
	 */
}
