package seneca.slee201.ttcbusschedule.xml;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import seneca.slee201.ttcbusschedule.model.RouteStop;
import seneca.slee201.ttcbusschedule.model.Direction;
import android.util.Log;

public class RouteStopParser {
	private List<RouteStop> stopList;
	
	public RouteStopParser() {}
	public RouteStopParser(List<RouteStop> lst) {
		this.stopList = lst;
	}
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
		try {
			if (qName.equals("stop") && attr.getLength() > 0) {
				RouteStop routeStop = new RouteStop();
				routeStop.setTag(attr.getValue("tag"));
				routeStop.setTitle(attr.getValue("title"));
				try {
					routeStop.setStopId(Integer.parseInt(attr.getValue("stopId")));
				} catch (Exception e) {
					Log.wtf("RouteStopParser", e.getMessage());
				}
				try {
					routeStop.setLon(Double.parseDouble(attr.getValue("lon")));
				} catch (Exception e) {
					Log.wtf("RouteStopParser", e.getMessage());
				}
				try {
					routeStop.setLat(Double.parseDouble(attr.getValue("lat")));
				} catch (Exception e) {
					Log.wtf("RouteStopParser", e.getMessage());
				}
				stopList.add(routeStop);
			}
			if (qName.equals("stop") && attr.getLength() > 0) {
				RouteStop routeStop = new RouteStop();
				routeStop.setTag(attr.getValue("tag"));
				routeStop.setTitle(attr.getValue("title"));
				try {
					routeStop.setStopId(Integer.parseInt(attr.getValue("stopId")));
				} catch (Exception e) {
					Log.wtf("RouteStopParser", e.getMessage());
				}
				try {
					routeStop.setLon(Double.parseDouble(attr.getValue("lon")));
				} catch (Exception e) {
					Log.wtf("RouteStopParser", e.getMessage());
				}
				try {
					routeStop.setLat(Double.parseDouble(attr.getValue("lat")));
				} catch (Exception e) {
					Log.wtf("RouteStopParser", e.getMessage());
				}
				stopList.add(routeStop);
			}
		} catch (Exception e) {
			Log.wtf("Error parsing 'route' xml", e.getMessage());
		}
	}
	
	
}
