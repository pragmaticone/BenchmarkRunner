/**
 * 
 */
package com.boundless.benchmark;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Soumya Sengupta
 * 
 */
public abstract class GeoserverCommunicator extends AbstractBenchmarkComponent {
	final static Logger logger = LoggerFactory
			.getLogger(GeoserverCommunicator.class);

	protected Object process(HttpUriRequest request, int expectedResponseCode)
			throws Exception {
		String[] geoserverHostAndPort = this.getProperties()
				.getProperty("geoserverHostAndPort").split(":");
		String geoserverHost = geoserverHostAndPort[0];
		int geoserverPort = Integer.parseInt(geoserverHostAndPort[1]);
		String username = this.getProperties().getProperty("username");
		String password = this.getProperties().getProperty("password");

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(geoserverHost, geoserverPort),
				new UsernamePasswordCredentials(username, password));

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			logger.debug("Executing request: " + request.getRequestLine());

			// Do basic authentication first.
			httpClient = HttpClients.custom()
					.setDefaultCredentialsProvider(credsProvider).build();

			// Execute the request.
			response = httpClient.execute(request);
			logger.debug("Response status line: " + response.getStatusLine());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return response.getStatusLine().getStatusCode() == expectedResponseCode;
	}

	public boolean checkIfWorkspaceExists(String workspaceName)
			throws Exception {
		String geoserverHostAndPort = this.getProperties().getProperty(
				"geoserverHostAndPort");

		HttpGet request = new HttpGet("http://" + geoserverHostAndPort
				+ "/geoserver/rest/workspaces/" + workspaceName);
		request.addHeader("Accept", ContentType.APPLICATION_JSON.toString());

		return (Boolean) process(request, 404);
	}

	public boolean deleteWorkspace(String workspaceName) throws Exception {
		String geoserverHostAndPort = this.getProperties().getProperty(
				"geoserverHostAndPort");

		HttpDelete request = new HttpDelete("http://" + geoserverHostAndPort
				+ "/geoserver/rest/workspaces/" + workspaceName
				+ "?recurse=true");
		request.addHeader("Accept", ContentType.APPLICATION_JSON.toString());

		return (Boolean) process(request, 200);
	}

	public boolean createWorkspace(String workspaceName) throws Exception {
		String geoserverHostAndPort = this.getProperties().getProperty(
				"geoserverHostAndPort");

		HttpPost request = new HttpPost("http://" + geoserverHostAndPort
				+ "/geoserver/rest/workspaces");
		request.addHeader("Accept", ContentType.APPLICATION_JSON.toString());
		request.setEntity(EntityBuilder.create()
				.setText("{'workspace': {'name': '" + workspaceName + "'}}")
				.setContentType(ContentType.APPLICATION_JSON).build());

		return (Boolean) process(request, 201);
	}

	public boolean createUrlBasedShapefileBackedDataStore(String workspaceName,
			String dataStoreName, String url) throws Exception {
		String geoserverHostAndPort = this.getProperties().getProperty(
				"geoserverHostAndPort");

		HttpPut request = new HttpPut("http://" + geoserverHostAndPort
				+ "/geoserver/rest/workspaces/" + workspaceName
				+ "/datastores/" + dataStoreName
				+ "/url.shp?configure=all&target=shp");
		request.addHeader("Content-type", "application/zip");
		request.setEntity(EntityBuilder.create().setText(url).build());

		return (Boolean) process(request, 201);
	}
}
