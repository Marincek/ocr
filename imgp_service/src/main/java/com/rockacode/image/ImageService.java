package com.rockacode.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageService {

	private List<ImageProcessor> processors;
	private BufferedImage image;
	
	public ImageService(MultipartFile file) {
		BufferedImage image;
		try {
			image = ImageIO.read(file.getInputStream());
			initService(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageService(BufferedImage image) {
		initService(image);
	}
	
	public void initService(BufferedImage image){
		 processors = new ArrayList<>();
		 this.image = image;
	}
	
	public void addProcessor(ImageProcessor processor){
		processors.add(processor);
	}
	
	public BufferedImage process(){
		for (ImageProcessor imageProcessor : processors) {
			image = imageProcessor.process(image);
		}
		return image;
	}
	
}
