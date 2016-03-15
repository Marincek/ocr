package com.rockacode.ocr;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteOcrService implements OcrService {

	private static final Logger logger = LoggerFactory.getLogger(RemoteOcrService.class);

	@Value("${ocr.server.address}")
	private String ocrServerAddress;

	private RestTemplate restTemplate;

	public RemoteOcrService() {
		restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
	}

	@Override
	public String process(byte[] imageByte, String lang) {
		logger.info("OCR request to " + ocrServerAddress);
		
		ByteArrayResource byteArrayResource = new ByteArrayResource(imageByte) {
	        @Override
	        public String getFilename() {
	            return "Image-"+UUID.randomUUID()+".jpg";
	        }
	    };
		
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", byteArrayResource);
		map.add("lang", lang);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(map, headers);
		ResponseEntity<String> responseText = restTemplate.exchange(ocrServerAddress + "/upload", HttpMethod.POST, requestEntity, String.class);

		logger.info("OCR text : " + responseText.getBody());
		return responseText.getBody();
	}

}
