package seneca.slee201.ttcbusschedule.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import android.util.Log;

import seneca.slee201.ttcbusschedule.model.Route;

/**
 * ReportsCache holds all report informations retrieved in the past
 * from the AssortmentServices, in order to minimize the data usage and
 * provide faster access to reports used. <br /><br /> 
 * The Map of Reports collects report IDs as keys and Reports as values.
 */
public class RoutesCache {
	
	/**
	 * Instance that the system will access in order to store and retrieve
	 * values from route cache
	 */
	private static RoutesCache instance = null;
	
	/** For sync use */
	private static Object syncObject = new Object();
	
	/** Route ID is the integer key, Route is the value */
	private Map<Integer, Route> routes;
	
	/**
	 * Instantiates the RoutesCache if it has not been instantiated and
	 * return the instance
	 * 
	 * @return instance that will work as a cache until cleared
	 */
	public static RoutesCache getInstance() {
		if (instance == null) {
			synchronized(syncObject) {
				if (instance == null) {
					instance = new RoutesCache();
				}
			}
		}
		return instance;
	}
	
	// below three are used in order to set policy on how the program
	// should deal with multiple entries from multiple threads
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock read  = readWriteLock.readLock();
	private final Lock write = readWriteLock.writeLock();
	
	/**
	 * Default and only constructor
	 */
	protected RoutesCache() {
		init();
	}
	
	/**
	 * Instantiates report map
	 */
	private void init() {
		routes = new HashMap<Integer, Route>();	
	}
	
	/**
	 * Retrieves a single Report from reports Map that matches the
	 * parameter value report ID. It returns a Report on success, null otherwise
	 * 
	 * @param reportId Report ID to search reports Map
	 * @return report found or null 
	 */
	public Route get(Integer routeId) {
		if (routeId == null) {
			Log.wtf("RoutesCache", "Input parameter for get(Integer) is null");
			return null;
		}		
		read.lock();
		Route value = null;
		try {
			 value = routes.get(routeId);
		} finally {
			read.unlock();
		}		
		return value;
	}
	
	public List<Route> getAll() {
		List<Route> allRoutes = new ArrayList<Route>();
		for (Integer key : routes.keySet()) {
			allRoutes.add(routes.get(key));
		}
		return allRoutes;
	}
	
	/**
	 * Stores a Report into the cache
	 * 
	 * @param r report to store to the cache
	 */
	public void set(Integer routeId, Route route) {
		if (routeId == null || route == null) {
			Log.wtf("RoutesCache", "Input parameter for set(Route) is null or empty");
			return;
		}
		write.lock();
		
		try {			
			routes.put(routeId, route);
		} finally {
			write.unlock();
		}
	}
	
	/**
	 * Returns true if cache is empty, false otherwise.
	 * 
	 * @return true if cache is empty, false otherwise.
	 */
	public Boolean isEmpty() {		
		return (routes == null || routes.isEmpty());
	}
	
	/**
	 * Retrieves all the report IDs stored in cache.
	 * 
	 * @return list of report IDs stored in cache.
	 */
	public List<Integer> getKeys() {
		List<Integer> res = new ArrayList<Integer>();
		res.addAll(routes.keySet());
		return res;
	}
}



