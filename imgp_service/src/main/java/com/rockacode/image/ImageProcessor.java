package com.rockacode.image;

import java.awt.image.BufferedImage;

public interface ImageProcessor {

	BufferedImage process(BufferedImage image);
	
	void setSuccessor(ImageProcessor imageProcessor);
	
	boolean hasSuccessor();
	
}
