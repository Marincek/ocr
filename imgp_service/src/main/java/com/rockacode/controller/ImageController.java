package com.rockacode.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rockacode.domain.BaseResponse;
import com.rockacode.domain.ResponsePhoto;
import com.rockacode.domain.ResponseText;
import com.rockacode.image.BlackWhiteProcessor;
import com.rockacode.image.DoubleResizeProcessor;
import com.rockacode.image.GrayscaleProcessor;
import com.rockacode.image.ImageService;
import com.rockacode.image.OpenCVProcessor;

@RestController
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Value("${ocr.server.address}")
	private String ocrServerAddress;

	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String provideProcessInfo() {
		return "You can process an image by posting to this same URL.";
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST, produces = "image/jpg")
	public @ResponseBody byte[] handleImageProcess(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "rezise", defaultValue = "false") Boolean rezise,
			@RequestParam(value = "grayscale", defaultValue = "false") Boolean grayscale,
			@RequestParam(value = "blackwhite", defaultValue = "false") Boolean blackwhite,
			@RequestParam(value = "noise", defaultValue = "false") Boolean noise) {
		if (!file.isEmpty()) {
			try {
				BufferedImage image = ImageIO.read(file.getInputStream());
				ImageService imageUrils = new ImageService(image);
				if (rezise)
					imageUrils.addProcessor(new DoubleResizeProcessor());
				if (grayscale)
					imageUrils.addProcessor(new GrayscaleProcessor());
				if (blackwhite)
					imageUrils.addProcessor(new BlackWhiteProcessor());
				if (noise)
					imageUrils.addProcessor(new OpenCVProcessor());

				image = imageUrils.process();

				return imageToByteArray(image);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("You failed to process => " + e.getMessage());
			}
		} else {
			logger.error("You failed to upload because the file was empty.");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value = "/processphoto", method = RequestMethod.POST)
	public @ResponseBody BaseResponse processPhoto(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "rezise", defaultValue = "false") Boolean rezise,
			@RequestParam(value = "grayscale", defaultValue = "false") Boolean grayscale,
			@RequestParam(value = "blackwhite", defaultValue = "false") Boolean blackwhite,
			@RequestParam(value = "noise", defaultValue = "false") Boolean noise) {
		if (!file.isEmpty()) {
			try {
				ImageService imageUrils = new ImageService(file);
				if (rezise)
					imageUrils.addProcessor(new DoubleResizeProcessor());
				if (grayscale)
					imageUrils.addProcessor(new GrayscaleProcessor());
				if (blackwhite)
					imageUrils.addProcessor(new BlackWhiteProcessor());
				if (noise)
					imageUrils.addProcessor(new OpenCVProcessor());

				ResponsePhoto responsePhoto = new ResponsePhoto();
				long time = System.currentTimeMillis();
				BufferedImage image = imageUrils.process();
				responsePhoto.setProcessingTime(System.currentTimeMillis()-time);

				byte[] imageInByte = imageToByteArray(image);
				responsePhoto.setPhoto(imageInByte);
				return responsePhoto;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("You failed to process => " + e.getMessage());
			}
		} else {
			logger.error("You failed to upload because the file was empty.");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value = "/processtext", method = RequestMethod.POST)
	public @ResponseBody BaseResponse processText(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "rezise", defaultValue = "false") Boolean rezise,
			@RequestParam(value = "grayscale", defaultValue = "false") Boolean grayscale,
			@RequestParam(value = "blackwhite", defaultValue = "false") Boolean blackwhite,
			@RequestParam(value = "noise", defaultValue = "false") Boolean noise) {
		if (!file.isEmpty()) {
			try {
				BufferedImage image = ImageIO.read(file.getInputStream());
				ImageService imageUrils = new ImageService(image);
				if (rezise)
					imageUrils.addProcessor(new DoubleResizeProcessor());
				if (grayscale)
					imageUrils.addProcessor(new GrayscaleProcessor());
				if (blackwhite)
					imageUrils.addProcessor(new BlackWhiteProcessor());
				if (noise)
					imageUrils.addProcessor(new OpenCVProcessor());

				ResponseText responsePhoto = new ResponseText();
				long time = System.currentTimeMillis();
				image = imageUrils.process();
				responsePhoto.setProcessingTime(System.currentTimeMillis()-time);

				responsePhoto.setText("");
				return responsePhoto;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("You failed to process => " + e.getMessage());
			}
		} else {
			logger.error("You failed to upload because the file was empty.");
			return null;
		}
		return null;
	}
	
	private byte[] imageToByteArray(BufferedImage image) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}
}
