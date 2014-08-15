package com.killer923.dataFetcher.net.api;

import com.killer923.dataFetcher.net.api.exception.ResponseException;

public interface RequestDispatcher
{
	public ResponseWrapper sendGETRequest(String url) throws ResponseException;
}
