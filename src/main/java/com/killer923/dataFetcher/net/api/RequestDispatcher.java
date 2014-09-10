package com.killer923.dataFetcher.net.api;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.protocol.HttpContext;

import com.killer923.dataFetcher.net.api.exception.ResponseException;

public interface RequestDispatcher
{
	public ResponseWrapper sendGETRequest(String url, Header[] headers) throws ResponseException;
	public ResponseWrapper sendGETRequest(String url, Header[] headers, ResponseHandler<? extends ResponseWrapper> handler, HttpContext context) throws ResponseException;

	public ResponseWrapper sendPOSTRequest(String url, HttpEntity content, Header[] headers, int timeout) throws ResponseException;
	public ResponseWrapper sendPOSTRequest(String url, HttpEntity content, Header[] headers, int timeout, ResponseHandler<? extends ResponseWrapper> responseHandler, HttpContext context) throws ResponseException;
}
