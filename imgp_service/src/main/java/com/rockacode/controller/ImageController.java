package com.rockacode.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rockacode.domain.ResponsePhoto;
import com.rockacode.domain.ResponseText;
import com.rockacode.image.ImageService;

@RestController
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String provideProcessInfo() {
		return "You can process an image by posting to this same URL.";
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST, produces = "image/jpg")
	public @ResponseBody byte[] handleImageProcess(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			logger.error("You failed to upload because the file was empty.");
			return null;
		}

		try {
			return imageService.process(file).getPhoto();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("You failed to process => " + e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/processphoto", method = RequestMethod.POST)
	public @ResponseBody ResponsePhoto processPhoto(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			logger.error("You failed to upload because the file was empty.");
			return null;
		}
		try {
			return imageService.process(file);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("You failed to process => " + e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/processtext", method = RequestMethod.POST)
	public @ResponseBody ResponseText processText(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "lang", defaultValue = "mkd") String lang) {
		if (file.isEmpty()) {
			logger.error("You failed to upload because the file was empty.");
			return null;
		}
		try {
			return imageService.process(file,lang);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("You failed to process => " + e.getMessage());
			return null;
		}
	}
}
