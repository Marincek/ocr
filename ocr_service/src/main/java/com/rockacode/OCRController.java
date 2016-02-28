package com.rockacode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OCRController {

	private static final Logger logger = LoggerFactory.getLogger(OCRController.class);

	@Autowired
	private TesseractProcessor processor;

	@Value("${cloud.server.address}")
	private String serverAddress;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam(value = "lang", defaultValue = "mkd") String lang,
			@RequestParam("file") MultipartFile file) {
		logger.info("Server address " + serverAddress);
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(lang)));
				stream.write(bytes);
				stream.close();
				logger.info("You successfully uploaded , language " + lang + "!");
				String text = processor.getTextFromImage(bytes, lang);
				return "Text from image is : " + text;
			} catch (Exception e) {
				logger.error("You failed to upload => " + e.getMessage());
				return "You failed to upload => " + e.getMessage();
			}
		} else {
			logger.error("You failed to upload because the file was empty.");
			return "You failed to upload because the file was empty.";
		}
	}
}
