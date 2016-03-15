package com.rockacode.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rockacode.domain.ResponsePhoto;
import com.rockacode.domain.ResponseText;
import com.rockacode.ocr.OcrService;

@Component
public class ImageService {

	@Autowired
	private OcrService ocrService;
	
	private ImageProcessor imageProcessor;
	
	private DoubleResizeProcessor doubleResizeProcessor = new DoubleResizeProcessor();
	private GrayscaleProcessor grayscaleProcessor = new GrayscaleProcessor();
	private BlackWhiteProcessor blackWhiteProcessor = new BlackWhiteProcessor();
	private OpenCVProcessor cvProcessor = new OpenCVProcessor();

	public ResponsePhoto process(MultipartFile file) throws IOException {
		BufferedImage image = ImageIO.read(file.getInputStream());
		initProcessors();
		return processToPhoto(image);
	}
	
	public ResponseText process(MultipartFile file, String lang) throws IOException {
		BufferedImage image = ImageIO.read(file.getInputStream());
		initProcessors();
		return processToText(image, lang);
	}
	
	private void initProcessors() {
		imageProcessor = doubleResizeProcessor;
		doubleResizeProcessor.setSuccessor(grayscaleProcessor);
		grayscaleProcessor.setSuccessor(blackWhiteProcessor);
	}

	private ResponsePhoto processToPhoto(BufferedImage image) throws IOException {
		ResponsePhoto photo = new ResponsePhoto();
		long startTime = System.currentTimeMillis();
		photo.setPhoto(processToByte(image));
		photo.setProcessingTime(System.currentTimeMillis() - startTime);
		return photo;
	}
	
	private ResponseText processToText(BufferedImage image, String lang) throws IOException {
		ResponseText responseText = new ResponseText();
		long startTime = System.currentTimeMillis();
		byte[] imageByte = processToByte(image);
		String text = ocrService.process(imageByte, lang);
		responseText.setProcessingTime(System.currentTimeMillis() - startTime);
		responseText.setText(text);
		return responseText;
	}
	
	private byte[] processToByte(BufferedImage image) throws IOException {
		return imageToByteArray(imageProcessor.process(image));
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
