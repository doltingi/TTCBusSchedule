package seneca.slee201.ttcbusschedule.controls;

import java.util.List;

import android.util.Log;

import seneca.slee201.ttcbusschedule.cache.RoutesCache;
import seneca.slee201.ttcbusschedule.io.IOUtility;
import seneca.slee201.ttcbusschedule.model.Route;
import seneca.slee201.ttcbusschedule.util.Network;

/**
 * MetricController sits between metric cache and tasks as communicator.
 * It will only work as to retrieve and to store metrics to the cache. 
 */
public class RouteController {
	
	/**
	 * Retrieves a route matching the routeId. It will return the found route
	 * when found in cache. If not, it will try to retrieve all routes from
	 * the server, and stores them into cache, then return the list.
	 * Will return null if it's still not found.
	 * 
	 * @param routeId Route ID to search within Routes map
	 * 
	 * @return result Route found
	 */
	public static Route get(Integer routeId) {		
		if (routeId == null) {
			Log.wtf("ReportController", "report ID is null");
			return null;
		}
	
		RoutesCache cache = RoutesCache.getInstance();
		if (cache == null) {
			Log.wtf("ReportController", "ReportsCache is not instantiated");			
		}
		Route r = cache.get(routeId);
		if (r == null) {
			init();
		}
		r = cache.get(routeId);
		return r; 
	}
	
	/**
	 * Same mechanism as get function, but retrieves all Routes instead of
	 * a single Route.
	 * 
	 * @param metricId Metric ID to search within Metrics0 Map
	 * 
	 * @return result Metrics0 found
	 */
	public static List<Route> getAll() {
		RoutesCache cache = RoutesCache.getInstance();
		if (cache == null) {
			Log.wtf("RouteController", "RoutesCache is not instantiated");			
		}
		List<Route> r = cache.getAll();
		if (r == null || r.size() <= 0) {
			init();
		}
		r = cache.getAll();
		return r;
	}
	
	/**
	 * Retrieves all routes from the web service and stores them in cache.
	 */
	public static void init() {
		RoutesCache cache = RoutesCache.getInstance();
		if (cache == null) {
			Log.wtf("RouteController", "RouteController is not instantiated");
			return;
		}
		List<Route> routes = null;
		routes = retrieve();
		for (Route r : routes) {
			cache.set(r.getRouteId(), r);			
		}
	}
	
	/**
	 * Calls IOUtility's retrieve Routes to parse the XML as Route List.
	 * 
	 * @return List<Route> list of all routes when successful, null otherwise.
	 */
	private static List<Route> retrieve() {
		try {
			return IOUtility.retrieveRoutes(Network.routeURL);
		} catch (Exception e) {
			Log.wtf("RouteController", "Failed to retrieve using Network.routeURL and exception caught is " + e);
		}
		return null;
	}
}
