package com.qa.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;
import com.sun.javafx.collections.MappingChange.Map;

public class RestClient {

	//1: GET method without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault(); //Creating a closeable client connection
		HttpGet httpGet = new HttpGet(url); // Get http request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); //Hitting the GET URL
		return closeableHttpResponse;
	}
	
	//1: GET method with Header
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault(); //Creating a closeable client connection
		HttpGet httpGet = new HttpGet(url); // Get http request
		
		for(java.util.Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); //Hitting the GET URL
		return closeableHttpResponse;
	}
	
}
