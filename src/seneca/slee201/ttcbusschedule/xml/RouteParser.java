package seneca.slee201.ttcbusschedule.xml;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import seneca.slee201.ttcbusschedule.model.Route;

import android.util.Log;

public class RouteParser extends DefaultHandler {
	private List<Route> lst;
	
	public RouteParser() {}
	public RouteParser(List<Route> lst) {
		this.lst = lst;
	}
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
		try {
			if (attr.getLength() > 0) {
				Route route = new Route();
				route.setName(attr.getValue("title"));
				try {
					route.setRouteId(Integer.parseInt(attr.getValue("tag")));
				} catch (Exception e) {
					Log.wtf("RouteParser", e.getMessage());
				}
				lst.add(route);
			}
		} catch (Exception e) {
			Log.wtf("Error parsing 'route' xml", e.getMessage());
		}
	}
	
	
}