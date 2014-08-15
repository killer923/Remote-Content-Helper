package com.killer923.dataFetcher.net.http;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.killer923.dataFetcher.net.api.RequestDispatcher;
import com.killer923.dataFetcher.net.api.exception.ResponseException;

public class HttpRequestDispatcher implements RequestDispatcher
{
	private int retryCount = 3;

	public HTTPResponseWrapper sendGETRequest(String url) throws ResponseException
	{
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		
		// Create a method instance.
		GetMethod method = new GetMethod(url);
		
		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(retryCount, false));

		HTTPResponseWrapper response = null;
		
		// Execute the method.
		try
		{
			int statusCode = client.executeMethod(method);
			byte[] responseBody = method.getResponseBody();
			if (statusCode == HttpStatus.SC_OK)
			{
				response = new HTTPResponseWrapper(statusCode, responseBody);
			} else
			{
				response = new HTTPResponseWrapper(statusCode, method.getStatusLine().toString(), responseBody);
			}
		} catch (HttpException httpe)
		{
			System.err.println("Protocol error");
			throw new ResponseException("Fatal protocol violation: " + httpe.getMessage(), httpe);
		} catch (IOException ioe)
		{
			System.err.println("Transport error");
			throw new ResponseException("Fatal transport error: " + ioe.getMessage(), ioe);
		} catch (RuntimeException re)
		{
			System.err.println("Processing error");
			throw new ResponseException("Got an ERROR while processing request: " + re.getMessage(), re);
		} finally
		{
			// Release the connection.
			method.releaseConnection();
		}
		return response;
	}
	
	public int getRetryCount()
	{
		return retryCount;
	}

	public void setRetryCount(int retryCount)
	{
		this.retryCount = retryCount;
	}
	
}
