package com.killer923.dataFetcher.net.http;

import java.util.Arrays;

import org.apache.http.Header;

import com.killer923.dataFetcher.net.api.ResponseWrapper;


public final class HttpResponseWrapper implements ResponseWrapper
{
	private final int statusCode;
	private final byte[] response;
	private final String statusMessage;
	private final Header[] headers;

	protected HttpResponseWrapper(int statusCode, byte[] response, Header[] responseHeaders)
	{
		this.statusCode = statusCode;
		this.response = response;
		this.statusMessage = "OK";
		this.headers = responseHeaders;
	}

	protected HttpResponseWrapper(int statusCode, String statusMessage, byte[] response, Header[] responseHeaders)
	{
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.response = response;
		this.headers = responseHeaders;
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

	public final Header[] getHeaders()
	{
		return Arrays.copyOf(headers, headers.length);
	}

	@Override
	public String toString()
	{
		return "HttpResponseWrapper [statusCode=" + statusCode + ", response=" + Arrays.toString(response) + ", statusMessage=" + statusMessage + ", headers=" + Arrays.toString(headers) + "]";
	}
}
