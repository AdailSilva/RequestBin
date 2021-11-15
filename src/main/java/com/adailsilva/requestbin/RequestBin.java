package com.adailsilva.requestbin;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestBin {

	/*
	 * Make a request to get started.
	 *
	 * cURL curl -X POST -d "Name=AdailSilva" http://localhost:8080/api
	 * 
	 * PowerShell powershell -NoLogo -Command
	 * "(New-Object System.Net.WebClient).DownloadFile('http://localhost:8080/api', 'C:\Windows\Temp\API-AdailSilva.txt')"
	 * 
	 */

	public String post() {

		int statusCode = 0;
		String uri = "https://ens5g39nfyln2n6.m.pipedream.net";
		String responseBodyAsString = "";

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(uri);
		Header header = new Header();

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode objectNode = objectMapper.createObjectNode();

		StringRequestEntity stringRequestEntity = null;

		header.setName("Content-Type");
		header.setValue("application/json; charset=UTF-8");
		header.setName("Accept");
		header.setValue("application/json");
		postMethod.addRequestHeader(header);

		objectNode.put("Name", "AdailSilva"); // Repeat as needed.
		objectNode.put("CPF", "000.000.000-00");
		objectNode.put("RG", "00000000000000");
		objectNode.put("Matrícula", "000000000");
		String json = objectNode.toString();

		try {

			stringRequestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
			postMethod.setRequestEntity(stringRequestEntity);

			statusCode = httpClient.executeMethod(postMethod);
			responseBodyAsString = postMethod.getResponseBodyAsString();
			postMethod.releaseConnection();

			System.out.println("Status Code --> " + statusCode);
//			System.out.println("Response Body --> " + responseBodyAsString);

		} catch (HttpException httpException) {
			httpException.printStackTrace();
			System.err.println("Fatal error: " + httpException.getMessage());
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.err.println("Fatal error: " + ioException.getMessage());
		} finally {
			postMethod.releaseConnection();
		}

		return responseBodyAsString;

	}

	public String get() {

		int statusCode = 0;
		String uri = "https://ens5g39nfyln2n6.m.pipedream.net";
		String responseBodyAsString = "";

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(uri);

		System.out.println(getMethod.getRequestHeaders());

		try {

			statusCode = httpClient.executeMethod(getMethod);

			byte[] responseBody = getMethod.getResponseBody();
			responseBodyAsString = new String(responseBody);

			System.out.println("Status Code --> " + statusCode);
//			System.out.println("Response Body --> " + new String(responseBody));

		} catch (Exception exception) {
			System.err.println("Fatal error: " + exception.getMessage());
			exception.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}

		return responseBodyAsString;

	}

}
