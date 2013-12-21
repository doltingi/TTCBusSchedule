package seneca.slee201.ttcbusschedule;

import java.util.List;

import com.example.ttcbusschedule.R;

import seneca.slee201.ttcbusschedule.model.Route;
import seneca.slee201.ttcbusschedule.task.AsyncTaskManager;
import seneca.slee201.ttcbusschedule.task.RouteListTask;
import seneca.slee201.ttcbusschedule.task.OnTaskCompleteListener;
import seneca.slee201.ttcbusschedule.task.TaskBase;

import seneca.slee201.ttcbusschedule.util.Network;
import seneca.slee201.ttcbusschedule.util.CustomDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RouteListActivity extends Activity implements OnTaskCompleteListener {

	private ListView list;
	private List<Route> routes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_list);
		
		list = (ListView)findViewById(R.id.route_list);
		
		if (Network.isOnline(this)) {
			AsyncTaskManager mAsyncTaskManager = new AsyncTaskManager(this, this);
			mAsyncTaskManager.setupTask(new RouteListTask(this), true);
		}
		else {
			CustomDialog.errorDialog(this, CustomDialog.noInternetMsg);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.route_list, menu);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onTaskComplete(TaskBase<?, ?> task) {
		Object o = null;
		try {
			o = task.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (o != null) {
			routes = (List<Route>) task.mResult;
			fillScreen();
		}
		else {
			CustomDialog.showToast(this, CustomDialog.networkErrMsg);
		}
	}

	private void fillScreen() {
		ListAdapter lAdapter = new ArrayAdapter<Route> (this, android.R.layout.simple_list_item_1, routes);
		list.setAdapter(lAdapter);
	}
}
