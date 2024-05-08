package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import kss.kssfooddroid.util.enuSeleccion_OpcMesa;

/**
 * Created by HENRY on 16/02/2015.
 */
public class dlgMesaOpciones extends android.support.v4.app.DialogFragment {
    public void dlMesaOpciones(){

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_mesa_opciones, null);

        //Botone
        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_opcMesa);
        Cancelbtn.setOnClickListener(OnClickListener);
        final Button btnImprCuenta_opcMesa= (Button) view.findViewById(R.id.btnImprCuenta_opcMesa);
        btnImprCuenta_opcMesa.setOnClickListener(OnClickListener);
        final Button btnVerCuenta_opcMesa= (Button) view.findViewById(R.id.btnVerCuenta_opcMesa);
        btnVerCuenta_opcMesa.setOnClickListener(OnClickListener);
        final Button btnCliente_opcMesa= (Button) view.findViewById(R.id.btnCliente_opcMesa);
        btnCliente_opcMesa.setOnClickListener(OnClickListener);
        final Button btnOpciones_opcMesa= (Button) view.findViewById(R.id.btnOpciones_opcMesa);
        btnOpciones_opcMesa.setOnClickListener(OnClickListener);

        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_mesa_in);
        alertDialogBuilder.setTitle(R.string.kssSeleccionaCliente);

        return alertDialogBuilder.create();
    }
    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.btnImprCuenta_opcMesa:
                    ((prueba_DlgVarios) getActivity()).opcMesa(enuSeleccion_OpcMesa.IMPRIMIR_CUENTA);
                    dismiss();
                    break;
                case R.id.btnVerCuenta_opcMesa:
                    ((prueba_DlgVarios) getActivity()).opcMesa(enuSeleccion_OpcMesa.MOSTRAR_CUENTA);
                    dismiss();
                    break;
                case R.id.btnCliente_opcMesa:
                    ((prueba_DlgVarios) getActivity()).opcMesa(enuSeleccion_OpcMesa.CLIENTE);
                    dismiss();
                    break;
                case R.id.btnOpciones_opcMesa:
                    ((prueba_DlgVarios) getActivity()).opcMesa(enuSeleccion_OpcMesa.OPCIONES);
                    dismiss();
                    break;
                case R.id.imgbtnCancel_opcMesa:
                    ((prueba_DlgVarios) getActivity()).opcMesa(enuSeleccion_OpcMesa.NO_SELECCION);
                    dismiss();
                    break;
            }
        }
    };
}
