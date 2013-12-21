package seneca.slee201.ttcbusschedule.task;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import seneca.slee201.ttcbusschedule.controls.RouteController;
import seneca.slee201.ttcbusschedule.model.Route;

public final class RouteListTask extends TaskBase<Void, List<Route>> {
    
	private Context context;
	
    /* UI Thread */
    public RouteListTask(Context ctxt) {
    	super();
    	context = ctxt;
    }
    
    /* Separate Thread */
    @Override
    protected List<Route> doInBackground(Void... args) {
    	List<Route> result = new ArrayList<Route>();
    	if (isCancelled()) {
        	result = null;
		}
		try {
			result = getRouteList();
		} catch (Exception e) {
			e.printStackTrace();
			mResult = null;
		}
		return result;
    }
    
    private List<Route> getRouteList() throws Exception {
    	Log.d("InitTask - Route", "Initializing RouteController");
		RouteController.init();
		Log.d("InitTask - Route", "Initialization Complete");
		return RouteController.getAll();
	}
}