package org.experimental_players.debitiApi.exceptions;

import ch.qos.logback.core.status.Status;

import java.util.Map;

/**
 * Eccezione da utilizzare per le eccezioni gestite
 *
 */
public class UserException extends Exception{

	private static final long serialVersionUID = 440303358316268499L;
	
	private Status status;
	
	private Map<String, String> map;
	
	private String message;
	
	public UserException() {
		super();
	}
	
	public UserException(Status status) {
		super();
		this.status = status;
	}

	public UserException(Status status, Map<String, String> map) {
		super();
		this.status = status;
		this.map = map;
	}
	
	public UserException(String msg) {
		this.message = msg;
	}

	public Status getStatus() {
		return status;
	}
	
	public Map<String, String> getReplace(){
		return map;
	}
	
	

}
