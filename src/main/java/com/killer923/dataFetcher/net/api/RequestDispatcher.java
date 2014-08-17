package com.killer923.dataFetcher.net.api;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.RequestEntity;

import com.killer923.dataFetcher.net.api.exception.ResponseException;

public interface RequestDispatcher
{
	public ResponseWrapper sendGETRequest(String url, Header[] headers) throws ResponseException;
	
	public ResponseWrapper sendPOSTRequest(String url, RequestEntity content, Header[] headers, int timeout) throws ResponseException;
}
