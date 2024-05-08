package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by HENRY on 19/02/2015.
 */
public class dlgMesashelp extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_mesas_help, null);

        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_mesasHelp);
        Cancelbtn.setOnClickListener(OnClickListener);

        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Ayuda sobre íconos módulo MESAS");
        alertDialogBuilder.setIcon(R.drawable.btn_circ_help);

        return alertDialogBuilder.create();
    }
    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.imgbtnCancel_mesasHelp:
                    dismiss();
                    break;
            }
        }
    };
}
