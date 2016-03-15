package com.rockacode.image;

import java.awt.image.BufferedImage;

public abstract class BaseImageProcessor implements ImageProcessor {

	private ImageProcessor imageProcessor;

	@Override
	public void setSuccessor(ImageProcessor imageProcessor) {
		this.imageProcessor = imageProcessor;
	}
	
	public ImageProcessor getSuccessor() {
		return imageProcessor;
	}
	
	@Override
	public boolean hasSuccessor() {
		return imageProcessor!=null;
	}

	public BufferedImage returnImage(BufferedImage image){
		if(hasSuccessor()){
			return getSuccessor().process(image);
		}else{
			return image;
		}
	}
	
}
