package HTTPClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostRequest {

	@Test
	public void postRequest() throws ClientProtocolException, IOException {

		String url = "https://reqres.in/api/users";
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = HttpClientBuilder.create().build();

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Cotent-Type", "application/json");

		// Added json-simple library dependency from maven repository
		JSONObject js = new JSONObject();
		js.put("name", "Chitra");
		js.put("id", "100");
		js.put("job", "QA");
		String jsonString = js.toJSONString();
		StringEntity entity = new StringEntity(jsonString);

		// this is payload
		httpPost.setEntity(entity);

		// adding all headers
		for (Entry<String, String> entry : hm.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		// getting a response body
		HttpResponse response = httpClient.execute(httpPost);

		int statusCode = response.getStatusLine().getStatusCode();
		// getting status code
		Assert.assertEquals(statusCode, 201);
		System.out.println("StatusCode is " + statusCode);

		// getiing json response payload.
		String JsonPayload = EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println(JsonPayload);

		Header[] allHeaders = response.getAllHeaders();
		HashMap<String, String> headers = new HashMap<String, String>();
		for (Header header : allHeaders) {
			headers.put(header.getName(), header.getValue());
		}
		System.out.println("response header is " + headers);

	}
}
