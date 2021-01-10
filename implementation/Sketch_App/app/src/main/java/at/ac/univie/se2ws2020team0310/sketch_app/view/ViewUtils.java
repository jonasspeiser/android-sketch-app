package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Utility class for displaying elements on UI
 */
public final class ViewUtils {

    private ViewUtils() {}

    /**
     * Utility method for showing a Toast with a message within the current Context
     * @param context   the current context
     * @param text      the text to display in the Toast
     */
    public static void showToast(Context context, String text){
        Toast textToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        textToast.show();
    }
}
