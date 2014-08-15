package com.killer923.dataFetcher.net.http.test;

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
		ResponseWrapper response= httpRequestDispatcher.sendGETRequest("http://google.com");
		System.out.println(response);
	}
}
