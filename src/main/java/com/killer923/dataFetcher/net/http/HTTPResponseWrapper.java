package com.killer923.dataFetcher.net.http;

import java.util.Arrays;

import com.killer923.dataFetcher.net.api.ResponseWrapper;

public class HTTPResponseWrapper implements ResponseWrapper
{
	private final int statusCode;
	private final byte[] response;
	private final String statusMessage;

	protected HTTPResponseWrapper(int statusCode, byte[] response)
	{
		this.statusCode = statusCode;
		this.response = response;
		this.statusMessage = "OK";
	}

	protected HTTPResponseWrapper(int statusCode, String statusMessage, byte[] response)
	{
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.response = response;
	}

	public final int getStatusCode()
	{
		return statusCode;
	}

	public final byte[] getResponse()
	{
		return response.clone();
	}

	public final String getStatusMessage()
	{
		return new String(statusMessage);
	}
	
	@Override
	public String toString()
	{
		return "HTTPResponseWrapper [statusCode=" + statusCode + ", response=" + Arrays.toString(response) + ", statusMessage=" + statusMessage + "]";
	}

}
