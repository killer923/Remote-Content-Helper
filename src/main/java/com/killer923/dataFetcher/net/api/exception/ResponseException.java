package com.killer923.dataFetcher.net.api.exception;

public class ResponseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2096421132476838395L;

	public ResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ResponseException(String message) {
		super(message);
	}	
}
