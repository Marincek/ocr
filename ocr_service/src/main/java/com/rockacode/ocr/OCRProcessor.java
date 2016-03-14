package com.rockacode.ocr;

import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.TesseractException;

public interface OCRProcessor {

	String getTextFromImage(BufferedImage bufferedImage, String lang) throws TesseractException;
	
}
