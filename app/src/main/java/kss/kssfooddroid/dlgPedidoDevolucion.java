package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import kss.kssfooddroid.util.ToastManager;
import kss.kssfooddroid.util.enuSeleccion_Devolucion;

import static java.lang.Integer.parseInt;

/**
 * Created by HENRY on 17/02/2015.
 */

public class dlgPedidoDevolucion extends DialogFragment {
    private int cantidadItem_Pedido=0;
    private enuSeleccion_Devolucion enuSeleccion;
    private NumberPicker numPickCanti_devol;

    public void dlgPedidoDevolucion(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_pedido_devolucion, null);
        final ImageButton Okbtn= (ImageButton) view.findViewById(R.id.imgbtnOK_devol);
        Okbtn.setOnClickListener(OnClickListener);
        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_devol);
        Cancelbtn.setOnClickListener(OnClickListener);

        //Construir botones
        final RadioGroup grpRadio= (RadioGroup) view.findViewById(R.id.radioGroup1);
        final TextView lblProducto= (TextView) view.findViewById(R.id.lblProducto_devol );

        cantidadItem_Pedido = getArguments().getInt("cantidadItem_Pedido");
        final String NombreItem_Pedido = getArguments().getString("NombreItem_Pedido");


        numPickCanti_devol = (NumberPicker) view.findViewById(R.id.numPickCanti_devol);
        numPickCanti_devol.setVisibility(View.GONE);
        numPickCanti_devol.setMinValue(0);
        final int canti= cantidadItem_Pedido-1;
        if (canti>0){
            numPickCanti_devol.setMaxValue(canti);
            numPickCanti_devol.setValue(canti);
        }else {
            numPickCanti_devol.setMaxValue(0);
            numPickCanti_devol.setValue(0);
        }
        enuSeleccion=enuSeleccion.ELIMINAR_ITEM;

        numPickCanti_devol.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cantidadItem_Pedido= numPickCanti_devol.getValue();
            }
        });
        //Asignr Valor lblProducto

        lblProducto.setText(String.format("%d %s",cantidadItem_Pedido,NombreItem_Pedido ));
        grpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                switch(checkedId){
                    case R.id.rdEliminarItem:
                        enuSeleccion=enuSeleccion.ELIMINAR_ITEM;
                        numPickCanti_devol.setVisibility(View.GONE);
                        break;
                    case R.id.rdEliminarTodos:
                        enuSeleccion=enuSeleccion.ELIMINAR_TODO;
                        numPickCanti_devol.setVisibility(View.GONE);
                        break;
                    case R.id.rdReducirCantidad:
                        enuSeleccion=enuSeleccion.REDUCIR_ITEM;
                        numPickCanti_devol.setVisibility(View.VISIBLE);
                        break;
                };
            }
        });

        // Crear El AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_circ_items);
        alertDialogBuilder.setTitle(R.string.kssDevolucionItem);
        return alertDialogBuilder.create();
    }


    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.imgbtnOK_devol:
                    // Realizar labores de OK
                    // ToastManager.show(getActivity(), "has presionado Aceptar",ToastManager.INFORMATION,0);
                   if (enuSeleccion== enuSeleccion_Devolucion.ELIMINAR_ITEM ||
                           enuSeleccion== enuSeleccion_Devolucion.ELIMINAR_TODO){
                       ((pedido) getActivity()).setEliminarItem(enuSeleccion);
                       ((pedido) getActivity()).setEliminarItem(enuSeleccion);
                   }else {
                       ((pedido) getActivity()).setEliminarItem(numPickCanti_devol.getValue());
                   }
                    dismiss();
                   break;
                case R.id.imgbtnCancel_devol:
                    //Realizar albores de Cancel
                    enuSeleccion= enuSeleccion_Devolucion.NO_SELECCION;
                    dismiss();
                    break;
            }
        }
    };

}

