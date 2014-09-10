package com.killer923.dataFetcher.net.http;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.killer923.dataFetcher.net.api.RequestDispatcher;
import com.killer923.dataFetcher.net.api.ResponseWrapper;
import com.killer923.dataFetcher.net.api.exception.ResponseException;


public class HttpRequestDispatcher implements RequestDispatcher
{
	private int retryCount = 3;

	@Override
	public ResponseWrapper sendGETRequest(String url, Header[] headers) throws ResponseException
	{
		return sendGETRequest(url, headers, null, null);
	}

	@Override
	public ResponseWrapper sendGETRequest(String url, Header[] headers, ResponseHandler<? extends ResponseWrapper> handler, HttpContext context) throws ResponseException
	{
		// Create an instance of DefaultHttpClient.
		CloseableHttpClient client = HttpClientBuilder.create().disableAutomaticRetries().setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, true)).build();
		
		// Create a method instance.
		HttpGet request = new HttpGet(url);
		//set headers
		request.setHeaders(headers);
		if (handler == null)
		{
			handler = new DefaultResponseHandler();
		}
		ResponseWrapper response;
		// Execute the method.
		try
		{
			response = client.execute(request, handler, context);
		} catch (ClientProtocolException httpe)
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
			request.releaseConnection();
			try
			{
				client.close();
			} catch (IOException e)
			{
				e.printStackTrace();
				System.out.println("retrying to close");
				try
				{
					client.close();
					System.out.println("closed successfully");
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		return response;
	}

	@Override
	public ResponseWrapper sendPOSTRequest(String url, HttpEntity content, Header[] headers, int timeout) throws ResponseException
	{
		return sendPOSTRequest(url, content, headers, timeout, null, null);
	}

	@Override
	public ResponseWrapper sendPOSTRequest(String url, HttpEntity content, Header[] headers, int timeout, ResponseHandler<? extends ResponseWrapper> responseHandler, HttpContext context) throws ResponseException
	{
		//prepare for request
		CloseableHttpClient client = HttpClientBuilder.create().disableAutomaticRetries().setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, true)).build();
		;
		HttpPost method = new HttpPost(url);
		method.setConfig(RequestConfig.custom().setExpectContinueEnabled(true).build());
		method.setHeaders(headers);
		method.setEntity(content);
		if (responseHandler == null)
		{
			responseHandler = new DefaultResponseHandler();
		}
		ResponseWrapper response = null;
		try
		{//make the request
			response = client.execute(method, responseHandler, context);
		} catch (ClientProtocolException httpe)
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

	static class DefaultResponseHandler implements ResponseHandler<ResponseWrapper>
	{
		@Override
		public ResponseWrapper handleResponse(HttpResponse response) throws IOException
		{
			ResponseWrapper interpretedResponse;
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
//			try
//			{
//				EntityUtils.consume(entity);
//			} catch (IOException e)
//			{
//				e.printStackTrace();
//			}
			if (statusCode >= 200 && statusCode < 300)
			{
				interpretedResponse = new HttpResponseWrapper(statusCode, EntityUtils.toByteArray(entity), response.getAllHeaders());
			} else
			{
				interpretedResponse = new HttpResponseWrapper(statusCode, response.getStatusLine().toString(), EntityUtils.toByteArray(entity),response.getAllHeaders());
			}
			return interpretedResponse;
		}
	}
}
