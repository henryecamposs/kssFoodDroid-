package kss.kssfooddroid.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import kss.kssfooddroid.R;

import static kss.kssfooddroid.R.drawable.btn_circ_minusr;
import static kss.kssfooddroid.R.drawable.btn_circ_okv;
import static kss.kssfooddroid.R.drawable.btn_circ_plusv;
import static kss.kssfooddroid.R.drawable.btn_error;
import static kss.kssfooddroid.R.drawable.btn_info;
import static kss.kssfooddroid.R.drawable.btn_warning;
import static kss.kssfooddroid.R.drawable.ksslogo;
import static kss.kssfooddroid.R.drawable.ksslogo_opaco;
import static kss.kssfooddroid.R.drawable.toast_background_black;
import static kss.kssfooddroid.R.drawable.toast_background_blue;
import static kss.kssfooddroid.R.drawable.toast_background_gray;
import static kss.kssfooddroid.R.drawable.toast_background_green;
import static kss.kssfooddroid.R.drawable.toast_background_red;
import static kss.kssfooddroid.R.drawable.toast_background_white;
import static kss.kssfooddroid.R.drawable.toast_background_yellow;

/**
 * Created by HENRY on 17/02/2015.
 * Enviar un Mostrar un Toast mensaje en un tiempo determinado
 *
 */
public class ToastManager {

    public static final int INFORMATION = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;
    public static final int ADD=3;
    public static final int OK=4;
    public static final int DEL=5;
    public static final int BLACK=6;
    public static final int WHITE=7;
    public static final int GRAY=8;



    /**
     * Funcion para mostrar Toast en pantalla.
     * @param context Contexto de la aplicacion
     * @param msj mensaje de toast
     * @param tipoMSJ tipo de mensaje
     * @param tiempoMSJ duración del mensaje
     */
    public static void show(Context context, String msj,
                            int tipoMSJ, int tiempoMSJ) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.toast_layout, null);

        TextView tv = (TextView) layout.findViewById(R.id.tvTexto);
        tv.setText(msj);

        LinearLayout llRoot =
                (LinearLayout) layout.findViewById(R.id.llRoot);

        Drawable img;
        int bg;

        switch (tipoMSJ) {
            case WARNING:
                img = context.getResources().getDrawable(btn_warning);
                bg = toast_background_yellow;
                break;
            case ERROR:
                img = context.getResources().getDrawable(btn_error);
                bg = toast_background_red;
                break;
            case ADD:
                img = context.getResources().getDrawable(btn_circ_plusv);
                bg = toast_background_green;
                break;
            case OK:
                img = context.getResources().getDrawable(btn_circ_okv);
                bg = toast_background_green;
                break;
            case DEL:
                img = context.getResources().getDrawable(btn_circ_minusr);
                bg = toast_background_red;
                break;
            case BLACK:
                img = context.getResources().getDrawable(ksslogo_opaco);
                bg = toast_background_black;
                break;
            case WHITE:
                img = context.getResources().getDrawable(ksslogo_opaco);
                bg = toast_background_white;
                break;
            case GRAY:
                img = context.getResources().getDrawable(ksslogo_opaco);
                bg = toast_background_gray;
                break;
            default:
                img = context.getResources().getDrawable(btn_info);
                bg = toast_background_blue;
                break;
        }
        tv.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        llRoot.setBackgroundResource(bg);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(tiempoMSJ);
        toast.setView(layout);
        toast.show();
    }
}