package src.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.src.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common_utils {
    private static src.Utils.Common_utils instance;
    private Context context;
    private MaterialAlertDialogBuilder dialog;

    private Common_utils(Context context) {
        this.context = context;
    }

    public static src.Utils.Common_utils getInstance() {
        return instance;
    }

    public static src.Utils.Common_utils initHelper(Context context) {
        if (instance == null)
            instance = new src.Utils.Common_utils(context);
        return instance;
    }

    // check connection to internet
    public boolean checkInternetConnection(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            internet_dialog(activity);
            return false;
        }
        return true;
    }

    // present error dialog
    private void internet_dialog(final Activity activity) {
        dialog = new MaterialAlertDialogBuilder(activity);
        dialog.setTitle(activity.getResources().getString(R.string.internet_dialog_title));
        dialog.setMessage(activity.getResources().getString(R.string.internet_supporting_text));
        dialog.setPositiveButton(activity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Respond to positive button press
                activity.finish();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                activity.finish();
            }
        });
        dialog.show();
    }

    /* presents error message OnCanceled */
    public void error_dialog(final Activity activity) {
        dialog = new MaterialAlertDialogBuilder(activity);
        dialog.setTitle(activity.getResources().getString(R.string.error_title));
        dialog.setMessage(activity.getResources().getString(R.string.dialog_error_message));
        dialog.setPositiveButton(activity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Respond to positive button press
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    // check if _date equals to current date
    public boolean checkDate(String _date) {
        Date date = new Date(); // This object contains the current date value
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter =
                new SimpleDateFormat("dd/MM/yyyy");
        return (formatter.format(date)).equals(_date);
    }
}
