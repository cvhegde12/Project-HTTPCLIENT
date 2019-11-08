package HTTPClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class PutRequest {

	@Test
	public void putRequest() throws ClientProtocolException, IOException {
		String url = "https://reqres.in/api/users/2";
		HttpPut httpPut = new HttpPut(url);
		HttpClient httpClient = HttpClientBuilder.create().build();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("name", "Alex");
		json.put("job", "Developer");
		json.put("id", "100");

		String jsonString = json.toJSONString();// converting the inputs into json string.
		StringEntity entity = new StringEntity(jsonString);// convert json string into http entity type
		// sending payload or sending json data
		httpPut.setEntity(entity);// parsing entity into set entity method

		// setting header part
		for (Entry<String, String> entry : hm.entrySet()) {

			httpPut.addHeader(entry.getKey(), entry.getValue());
		}

		// hitting the url
		HttpResponse response = httpClient.execute(httpPut);

		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("the status code is " + statusCode);

		// getting payload from server
		String stringJson = EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println("payload is " + stringJson);

		//
		HashMap<String, String> header = new HashMap<String, String>();
		Header[] headers = response.getAllHeaders();
		for (Header allHeaders : headers) {
			header.put(allHeaders.getName(), allHeaders.getValue());
		}

		System.out.println("Headers:" + headers);
	}
}
