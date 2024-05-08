package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import kss.kssfooddroid.util.enuSeleccion_OpcPedido;

/**
 * Created by HENRY on 17/02/2015.
 */
public class dlgPedidoOpciones extends DialogFragment {

    private enuSeleccion_OpcPedido enuSeleccion;
    public void dlgPedidoOpciones(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_pedido_opciones, null);
       //Botones
        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_opcPedido);
        Cancelbtn.setOnClickListener(OnClickListener);
        final Button btnImprCuenta_opcMesa= (Button) view.findViewById(R.id.btnImprCuenta_opcPedido);
        btnImprCuenta_opcMesa.setOnClickListener(OnClickListener);
        final Button btnVerCuenta_opcMesa= (Button) view.findViewById(R.id.btnVerCuenta_opcPedido);
        btnVerCuenta_opcMesa.setOnClickListener(OnClickListener);
        final Button btnCliente_opcMesa= (Button) view.findViewById(R.id.btnCliente_opcPedido);
        btnCliente_opcMesa.setOnClickListener(OnClickListener);
        final Button btnOpciones_opcMesa= (Button) view.findViewById(R.id.btnOpciones_opcPedido);
        btnOpciones_opcMesa.setOnClickListener(OnClickListener);
        final TextView lblNombreMesa = (TextView) view.findViewById(R.id.lblmesa_opcPedido);
        final String NombreMesa = getArguments().getString("NombreMesa");
        lblNombreMesa.setText(NombreMesa);
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_circ_itemprecio);
        alertDialogBuilder.setTitle(R.string.kssOpcionesPedido);


        return alertDialogBuilder.create();
    }

    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.btnImprCuenta_opcPedido:
                    ((pedido) getActivity())._enuSeleccion_OpcPedido=enuSeleccion_OpcPedido.IMPRIMIR_COMANDA;
                    dismiss();
                    break;
                case R.id.btnVerCuenta_opcPedido:
                    ((pedido) getActivity())._enuSeleccion_OpcPedido=enuSeleccion_OpcPedido.MOSTRAR_CUENTA;
                    dismiss();
                    break;
                case R.id.btnCliente_opcPedido:
                    ((pedido) getActivity())._enuSeleccion_OpcPedido=enuSeleccion_OpcPedido.CLIENTE;
                    dismiss();
                    break;
                case R.id.btnOpciones_opcPedido:
                    ((pedido) getActivity())._enuSeleccion_OpcPedido=enuSeleccion_OpcPedido.OPCIONES;
                    dismiss();
                    break;
                case R.id.imgbtnCancel_opcPedido:
                    //Realizar albores de Cancel
                    ((pedido) getActivity())._enuSeleccion_OpcPedido=enuSeleccion_OpcPedido.NO_SELECCION;
                    dismiss();
                    break;
            }
        }
    };
}
