package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageButton;

import kss.kssfooddroid.util.ToastManager;

/**
 * Created by HENRY on 19/02/2015.
 */
public class dlgPedidoHelp extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_pedido_help, null);

        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_pedidoHelp);
        Cancelbtn.setOnClickListener(OnClickListener);

        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Ayuda sobre íconos módulo PEDIDO.");
        alertDialogBuilder.setIcon(R.drawable.btn_circ_help);

        return alertDialogBuilder.create();
    }
    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.imgbtnCancel_pedidoHelp:
                    dismiss();
                    break;
            }
        }
    };
}
