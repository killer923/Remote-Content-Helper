package com.killer923.dataFetcher.net.http.test;

import org.apache.commons.httpclient.Header;
import org.junit.Before;
import org.junit.Test;

import com.killer923.dataFetcher.net.api.ResponseWrapper;
import com.killer923.dataFetcher.net.api.exception.ResponseException;
import com.killer923.dataFetcher.net.http.HttpRequestDispatcher;

public class RequestTest
{
	HttpRequestDispatcher httpRequestDispatcher;
	
	@Before
	public void setUp(){
		 httpRequestDispatcher=new HttpRequestDispatcher();
	}
	
	@Test
	public void testSendGETRequest() throws ResponseException
	{
		ResponseWrapper response= httpRequestDispatcher.sendGETRequest("http://cmsv.indiatimes.com/cmslite/api/lite/dummyGet");
		System.out.println(response);
		System.out.println(new String(response.getResponse()));
		Header[] headers = response.getHeaders();
		for(Header header : headers)
		{
			System.out.println(header);
		}
	}
}
