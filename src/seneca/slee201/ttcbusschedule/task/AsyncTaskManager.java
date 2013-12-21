package seneca.slee201.ttcbusschedule.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

public final class AsyncTaskManager implements IProgressTracker, OnCancelListener {

	private final OnTaskCompleteListener mTaskCompleteListener;
	private ProgressDialog mProgressDialog;
	private TaskBase<?,?> mAsyncTask;
	private Context context;

	public AsyncTaskManager(Context context, OnTaskCompleteListener taskCompleteListener) {
		// Save reference to complete listener (activity)
		mTaskCompleteListener = taskCompleteListener;
		this.context = context;
	}

	public void setupTask(TaskBase<?,?> asyncTask, boolean enableDialog, boolean cancelable) {
		if (enableDialog) {
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(cancelable);
			mProgressDialog.setOnCancelListener(this);
		}
		else {
			mProgressDialog = null;
		}
		
		// Keep task
		mAsyncTask = asyncTask;
		// Wire task to tracker (this)
		mAsyncTask.setProgressTracker(this, context);
		// Start task
		mAsyncTask.executeNonGenericWay();
	}
	
	public void setupTask(TaskBase<?,?> asyncTask, boolean enableDialog) {
		if (enableDialog){
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(true);
			mProgressDialog.setOnCancelListener(this);
		}
		else {
			mProgressDialog = null;
		}
		
		// Keep task
		mAsyncTask = asyncTask;
		// Wire task to tracker (this)
		mAsyncTask.setProgressTracker(this, context);
		// Start task
		mAsyncTask.executeNonGenericWay();
	}


	@Override
	public void onProgress(String message) {
		// Show dialog if it wasn't shown yet or was removed on configuration (rotation) change
		if (mProgressDialog != null) {
			if (!mProgressDialog.isShowing()) {
				mProgressDialog.show();
			}
			// Show current message in progress dialog
			mProgressDialog.setMessage(message);
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		// Cancel task
		mAsyncTask.cancel(true); 
		// Notify activity about completion
		mTaskCompleteListener.onTaskComplete(mAsyncTask);
		// Reset task
		mAsyncTask = null;
	}

	@Override
	public void onComplete() {
		// Close progress dialog
		if (mProgressDialog != null) mProgressDialog.dismiss();
		// Notify activity about completion
		mTaskCompleteListener.onTaskComplete(mAsyncTask);
		// Reset task
		mAsyncTask = null;
	}

	public Object retainTask() {
		// Detach task from tracker (this) before retain
		if (mAsyncTask != null) {
			mAsyncTask.setProgressTracker(null, context);
		}
		// Retain task
		return mAsyncTask;
	}

	public void handleRetainedTask(Object instance) {
		// ADDED Dismiss any unwanted Dialog before subsequent refresh etc.
		if (mProgressDialog != null) mProgressDialog.dismiss();
		// Restore retained task and attach it to tracker (this)
		if (instance instanceof TaskBase<?,?>) {
			mAsyncTask = (TaskBase<?,?>) instance;
			mAsyncTask.setProgressTracker(this, context);
		}
	}

	public boolean isWorking() {
		// Track current status
		return mAsyncTask != null;
	}
}