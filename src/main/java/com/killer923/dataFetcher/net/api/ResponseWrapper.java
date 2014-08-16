package com.killer923.dataFetcher.net.api;

import org.apache.commons.httpclient.Header;

/**
 * All classes implementing this {@link ResponseWrapper} are immutable.
 * 
 * @author Abhishek.Bhatia
 *
 */
public interface ResponseWrapper
{
	/**
	 * @return the status specified by the response.
	 */
	public int getStatusCode();
	/**
	 * @return actual response.
	 */
	public byte[] getResponse();
	/**
	 * @return the status message of respose.
	 */
	public String getStatusMessage();
	/**
	 * 
	 * @return the Headers recieved in response
	 */
	public Header[] getHeaders();
}
