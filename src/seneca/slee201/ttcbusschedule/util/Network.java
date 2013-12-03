package seneca.slee201.ttcbusschedule.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;

public class Network {

	// public static final String HOST = "192.168.79.37";
	public static final String HOST = "webservices.nextbus.com";

	public static String prefix = "http://" + HOST + "/service/publicXMLFeed";
	public static String agency = "ttc";
	
	// xml webservice URL
	public static String routeURL = prefix + "?command=routeList&a=" + agency;
	public static String routeStopURL = prefix + "?command=routeConfig&a=" + agency + "&r=";
	
	public static boolean isOnline(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

		boolean b = cm.getActiveNetworkInfo() != null && 
				cm.getActiveNetworkInfo().isConnected();

		return b;
	}
	
	public static String getAllRoutes() throws Exception {
		return httpClient(routeURL);
	}
	
	public static String getRouteStops(String id) throws Exception {
		return httpClient(routeStopURL + id);
	}
	
	private static String httpClient(String url) throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader
					(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
