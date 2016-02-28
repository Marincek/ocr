package com.rockacode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class TesseractProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TesseractProcessor.class);
	
	public static String DATAPATH;

	public String getTextFromImage(byte[] image, String lang) throws IOException, TesseractException {
		InputStream in = new ByteArrayInputStream(image);
		BufferedImage bufferedImage = ImageIO.read(in);

		ITesseract instance = new Tesseract(); 
		// ITesseract instance = new Tesseract1(); // JNA Direct Mapping

		instance.setDatapath(DATAPATH);
		instance.setLanguage(lang);
		
		long time = System.currentTimeMillis();
		String result = instance.doOCR(bufferedImage);
		
		logger.info("OCR in : "+(System.currentTimeMillis()-time) + " ms, text length : "+result.length());
		
		return result;
	}

}
