package com.rockacode.ocr;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class TesseractProcessor implements OCRProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TesseractProcessor.class);
	private ITesseract instance;
	
	private String datapath;
	
	public TesseractProcessor() {
		instance = new Tesseract(); 
	}

	public String getTextFromImage(BufferedImage bufferedImage, String lang) throws TesseractException{
		logger.info("TesseractProcessor DATAPATH : " + datapath);
		instance.setLanguage(lang);
		
		long time = System.currentTimeMillis();
		String result = instance.doOCR(bufferedImage);
		
		logger.info("OCR in : "+(System.currentTimeMillis()-time) + " ms, text length : "+result.length());
		
		return result;
	}
	
	@Value("${tessaract.datapath}")
	public void setDatapath(String datapath){
		this.datapath = datapath;
		instance.setDatapath(datapath);
	}

}
