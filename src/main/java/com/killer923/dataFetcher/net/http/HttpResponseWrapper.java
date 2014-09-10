package com.killer923.dataFetcher.net.http;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.killer923.dataFetcher.net.api.ResponseWrapper;


public final class HttpResponseWrapper implements ResponseWrapper
{
	private final int statusCode;
	private final HttpEntity response;
	private final String statusMessage;
	private Header[] headers;

	protected HttpResponseWrapper(int statusCode, HttpEntity response, Header[] responseHeaders)
	{
		this.statusCode = statusCode;
		this.response = response;
		this.statusMessage = "OK";
		this.headers = responseHeaders;
	}

	protected HttpResponseWrapper(int statusCode, String statusMessage, HttpEntity response)
	{
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.response = response;
	}


	@Override
	public final int getStatusCode()
	{
		return statusCode;
	}

	@Override
	public final HttpEntity getResponse()
	{
		return response;
	}

	@Override
	public final String getStatusMessage()
	{
		return new String(statusMessage);
	}

	@Override
	public Header[] getHeaders()
	{
		return Arrays.copyOf(headers, headers.length);
	}

	@Override
	public String toString()
	{
		String responseToString=null;
		try
		{
			responseToString = EntityUtils.toString(response);
		} catch (ParseException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "HttpResponseWrapper [statusCode=" + statusCode + ", response=" + responseToString + ", statusMessage=" + statusMessage + ", headers=" + Arrays.toString(headers) + "]";
	}
}
