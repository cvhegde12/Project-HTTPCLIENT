package HTTPClient;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequest {

	@Test
	public void getRequest() throws ClientProtocolException, IOException {
		String url = "https://reqres.in/api/users?page=2";
		HttpGet httpRequest = new HttpGet(url); // passing the url

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse getResponse = httpClient.execute(httpRequest); // hitting the url. this getResponse holds header,
																	// payload etc.
		int statusCode = getResponse.getStatusLine().getStatusCode();

		// getting status code
		Assert.assertEquals(statusCode, 200);
		System.out.println("StatusCode is " + statusCode);

		// getiing json response payload.
		String JsonPayload = EntityUtils.toString(getResponse.getEntity(), "UTF-8");
		System.out.println(JsonPayload);

		Header[] allHeaders = getResponse.getAllHeaders();
		HashMap<String, String> headers = new HashMap<String, String>();
		for (Header header : allHeaders) {
			headers.put(header.getName(), header.getValue());
		}
		System.out.println("response header is " + headers);
	}
}
