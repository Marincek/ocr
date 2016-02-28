package com.rockacode.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {

	private List<ImageProcessor> processors;
	private BufferedImage image;
	
	public ImageUtils(BufferedImage image) {
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
