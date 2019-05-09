package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	TestBase testBase;
	RestClient restClient;
	String uri;
	String url;
	String requestURL;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod()
	public void setUP(){
		testBase = new TestBase();// This will call testBase constructor and initialize properties file
		uri = prop.getProperty("urlapi") + prop.getProperty("serviceurl");
	}
	
	@Test
	public void getAPITestWithoutHeader() throws ClientProtocolException, IOException {
		setUP();
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(uri);
		System.out.println("***************Get API Calls WITHOUT headers*******************");
		//1.a: Getting Status Code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Response Status Code -----> "+statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STSTUS_CODE_200, "Status code is not 200");
				
				//1.b: Getting JSON response string
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");// To get the response we have a class EntityUtills which returns String. and we have to extract the response from httpresponse object with getEntity() method
				JSONObject responseJson = new JSONObject(responseString);//Now Converting String response into JSON
				System.out.println("Response JSON from API -----> "+responseJson);
				
				//Now we have complete JSON in response but we wanted to fetch any specific value from the entire response like: 'per_page' & 'total'. Then we need to parse the JSON and then fetch the required data like below.
				
				//Single Value Assertion:
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");//This method code is written inside util class.
				System.out.println("Value of per page -----> " + perPageValue);
				Assert.assertEquals(perPageValue,"3");	
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of total -----> " + totalValue);
				Assert.assertEquals(totalValue,"12");
				
				//Multiple Value assertion
				String lastName = TestUtil.getValueByJPath(responseJson, "data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "data[0]/avatar");
				String firstname = TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
				System.out.println("Value of Last Name -----> " + lastName);
				System.out.println("Value of ID -----> " + id);
				System.out.println("Value of Avatar -----> " + avatar);
				System.out.println("Value of First Name -----> " + firstname);
				
				//1.c: Getting all headers
				Header[] headerArray = closeableHttpResponse.getAllHeaders(); //getAllHearders() is returning header array. So we need to create header array to receive the same.
				HashMap<String, String> allHeaders = new HashMap<String, String>();// Creating HashMap to store Key value pair for hearders and later use this map to iterate and print
				for(Header header: headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array ----->"+allHeaders);
	}
	
	@Test
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		setUP();
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type","application/json");
		closeableHttpResponse = restClient.get(uri,headerMap);
		System.out.println("");
		System.out.println("***************Get API Calls WITH headers*******************");
		//1.a: Getting Status Code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Response Status Code -----> "+statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STSTUS_CODE_200, "Status code is not 200");
				
				//1.b: Getting JSON response string
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");// To get the response we have a class EntityUtills which returns String. and we have to extract the response from httpresponse object with getEntity() method
				JSONObject responseJson = new JSONObject(responseString);//Now Converting String response into JSON
				System.out.println("Response JSON from API -----> "+responseJson);
				
				//Now we have complete JSON in response but we wanted to fetch any specific value from the entire response like: 'per_page' & 'total'. Then we need to parse the JSON and then fetch the required data like below.
				
				//Single Value Assertion:
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");//This method code is written inside util class.
				System.out.println("Value of per page -----> " + perPageValue);
				Assert.assertEquals(perPageValue,"3");	
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of total -----> " + totalValue);
				Assert.assertEquals(totalValue,"12");
				
				//Multiple Value assertion
				String lastName = TestUtil.getValueByJPath(responseJson, "data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "data[0]/avatar");
				String firstname = TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
				System.out.println("Value of Last Name -----> " + lastName);
				System.out.println("Value of ID -----> " + id);
				System.out.println("Value of Avatar -----> " + avatar);
				System.out.println("Value of First Name -----> " + firstname);
				
				//1.c: Getting all headers
				Header[] headerArray = closeableHttpResponse.getAllHeaders(); //getAllHearders() is returning header array. So we need to create header array to receive the same.
				HashMap<String, String> allHeaders = new HashMap<String, String>();// Creating HashMap to store Key value pair for hearders and later use this map to iterate and print
				for(Header header: headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array ----->"+allHeaders);
	}
}
