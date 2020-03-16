package nasa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.image.Image;

public class NASAClient {
	HashMap<String, String> mapParameters = new HashMap<String, String>();
	private String szAPIKey;
	private final String szNASA = "https://api.nasa.gov/planetary/earth/imagery/?";
	private boolean isDownCloud = false;

	private JSONObject launch() throws IOException, InterruptedException, NASAResponseException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(szNASA);
		for (Entry<String, String> entry : mapParameters.entrySet())
			urlBuilder.append("&" + entry.getKey() + "=" + entry.getValue());

		HttpRequest.Builder paramBuilder = HttpRequest.newBuilder().uri(URI.create(urlBuilder.toString()));
		HttpRequest req = paramBuilder.GET().build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
		if (resp.statusCode() > 400) 
			throw new NASAResponseException(resp.uri().toString(), resp.statusCode(), resp.body());
		

		JSONObject toRet = new JSONObject(resp.body());
		return toRet;
	}

	private void addParam(String key, String val) {
		mapParameters.put(key, val);
	}

	private Image parseImage(String dest) throws IOException, InterruptedException {
		HttpRequest.Builder imageURL = HttpRequest.newBuilder().uri(URI.create(dest));
		HttpRequest req = imageURL.GET().build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<byte[]> resp = client.send(req, BodyHandlers.ofByteArray());
	
		return new Image(new ByteArrayInputStream(resp.body()));
	}

	public NASAClient(String szAPIKey) {
		this.szAPIKey = szAPIKey;
	}

	public void setClouds(boolean val) {
		isDownCloud = val;
	}

	public NASAData getData(String date, double dLat, double dLong, double dDim)
			throws IOException, InterruptedException, NASAResponseException, ParseException {
		mapParameters.clear();
		addParam("cloud_score", Boolean.toString(isDownCloud));
		if (!date.isEmpty()) {
			String datePattern = "yyyy-MM-dd";
			new SimpleDateFormat(datePattern).parse(date);
			addParam("date", date);
		}

		addParam("api_key", szAPIKey);
		addParam("lon", Double.toString(dLong));
		addParam("lat", Double.toString(dLat));

		if (dDim != 0) {
			if (dDim > 1 || dDim <= 0)
				throw new IllegalArgumentException();

			addParam("dim", Double.toString(dDim));
		}

		JSONObject jsonData = launch();

		NASAData toRet = new NASAData();
		if (isDownCloud) {
			try {
				toRet.dCloudScale = jsonData.getDouble("cloud_score");
			} catch (JSONException e) {
				toRet.dCloudScale = -1;
			}
		}

		toRet.szDate = jsonData.getString("date");
		toRet.szID = jsonData.getString("id");
		toRet.dLatitude = dLat;
		toRet.dLongtitude = dLong;
		try {
			JSONObject objResource = jsonData.getJSONObject("resource");
			toRet.szDataSet = objResource.optString("dataset");
			toRet.szPlanet = objResource.optString("planet");
		} catch (JSONException e) {

		}
		toRet.szService = jsonData.optString("service_version");
		toRet.szURL = jsonData.getString("url");
		toRet.NASAImage = parseImage(toRet.szURL);
		mapParameters.clear();
		return toRet;
	}

}