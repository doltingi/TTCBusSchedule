package seneca.slee201.ttcbusschedule.io;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import seneca.slee201.ttcbusschedule.model.Route;
import seneca.slee201.ttcbusschedule.xml.RouteParser;

/**
 * IOUtility parses each data model from XML to POJO class.
 */
public class IOUtility {
	
	public static List<Route> retrieveRoutes(String content) {
		List<Route> lst = new ArrayList<Route>();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();
			sp.parse(new InputSource(new StringReader(content)), new RouteParser(lst));
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		return lst;
	}
}
