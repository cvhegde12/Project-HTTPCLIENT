package HTTPClient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteRequest {

	@Test
	public void deleteRequest() throws ClientProtocolException, IOException {
		String url = "https://reqres.in/api/users/2";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete httpDelete = new HttpDelete(url);

		HttpResponse response = httpClient.execute(httpDelete);

		int statusCode = response.getStatusLine().getStatusCode();

		Assert.assertEquals(statusCode, 204);

		System.out.println(statusCode);

	}

}
