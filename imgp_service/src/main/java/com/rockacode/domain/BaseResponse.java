package com.rockacode.domain;

public abstract class BaseResponse {
	
	protected long processingTime;

	public long getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}

}
