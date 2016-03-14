package com.rockacode.ocr;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.TesseractException;

@Service
public class OCRService {

	private OCRProcessor processor;
	
	@Autowired
	public OCRService(OCRProcessor processor) {
		this.processor = processor;
	}

	public String doOcrProcessing(byte[] file, String language) throws IOException, TesseractException{
		return processor.getTextFromImage(ImageIO.read(new ByteArrayInputStream(file)), language);
	}
	
}
