package seneca.slee201.ttcbusschedule.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


public class CustomDialog {

	public static String noInternetMsg = "No internet connection detected";
	public static String networkErrMsg = "There was a problem with the network";
	
	public static void errorDialog(Context c, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        
        dialog.setMessage(msg); 
        dialog.setCancelable(true);
        dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
        dialog.create();
        dialog.show();
    }
	
	public static void showToast(Context c, String msg) {
		Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }
}
