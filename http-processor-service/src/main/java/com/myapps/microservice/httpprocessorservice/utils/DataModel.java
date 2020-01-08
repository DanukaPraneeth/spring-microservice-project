package com.myapps.microservice.httpprocessorservice.utils;

import org.springframework.stereotype.Component;

@Component
public class DataModel {
	
	private String msisdn;
	private String operator;
	private String operation;
	
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public String getMsisdn() {
		return this.msisdn;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}
}
