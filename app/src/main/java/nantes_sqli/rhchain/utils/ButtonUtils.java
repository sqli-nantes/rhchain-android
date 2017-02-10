package nantes_sqli.rhchain.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.activities.MainActivity;

/**
 * Created by mcame on 09/02/17.
 */

public class ButtonUtils {

    public static void modifierEtatBouton(Context c, View btn_submButton, boolean enabled){


        int color = ContextCompat.getColor(c, R.color.lessdarkgrey);
        if (enabled) {
            color = ContextCompat.getColor(c, R.color.colorSqli);
        }

        // MOdification du fond du bouton http://www.41post.com/5094/programming/android-change-color-of-the-standard-button-inside-activity
        btn_submButton.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

        btn_submButton.setEnabled(enabled);
        btn_submButton.invalidate();
    }
}
