package com.killer923.dataFetcher.net.http.test;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
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
	public void testSendGETRequest() throws ResponseException, ParseException, IOException
	{
		ResponseWrapper response= httpRequestDispatcher.sendGETRequest("http://httpbin.org/get",null);
		System.out.println(response);
		Header[] headers = response.getHeaders();
		System.out.println(Arrays.toString(headers));
	}
}
