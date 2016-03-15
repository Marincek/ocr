package com.rockacode.ocr;

public interface OcrService {

	public String process(byte[] imageByte, String lang);
	
}
