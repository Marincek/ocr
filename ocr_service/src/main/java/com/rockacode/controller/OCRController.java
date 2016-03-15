package com.rockacode.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rockacode.ocr.OCRService;

import net.sourceforge.tess4j.TesseractException;

@RestController
public class OCRController {

	private static final Logger logger = LoggerFactory.getLogger(OCRController.class);

	@Autowired
	private OCRService service;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String doOcrProcessing(@RequestParam(value = "lang", defaultValue = "mkd") String lang,
			@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			logger.error("You failed to upload because the file was empty.");
			return "You failed to upload because the file was empty.";
		}

		try {
			return service.doOcrProcessing(file.getBytes(), lang);
		} catch (IOException e) {
			logger.error("IOException , probem in reading file form byte[]");
			e.printStackTrace();
			return e.getMessage();
		} catch (TesseractException e) {
			logger.error("TesseractException, problem in doOCR");
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
