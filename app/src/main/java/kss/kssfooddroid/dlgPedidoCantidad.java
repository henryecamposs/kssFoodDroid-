package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import kss.kssfooddroid.util.ToastManager;

/**
 * Created by HENRY on 16/02/2015.
 */
public class dlgPedidoCantidad extends android.support.v4.app.DialogFragment {
    private int cantidadItem_Pedido = 0;
    private NumberPicker numPick;

    public void dlgPedidoCantidad() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_pedido_cantidad, null);

        cantidadItem_Pedido = getArguments().getInt("cantidadItem_Pedido");
        final String NombreItem_Pedido = getArguments().getString("NombreItem_Pedido");

        numPick = (NumberPicker) view.findViewById(R.id.npickCantidad_canti);
        numPick.setMaxValue(99);
        final int canti= cantidadItem_Pedido+1;
        if (canti > 0) {
            numPick.setMinValue(canti);
            numPick.setValue(canti);
        }

        //Asignr Valor lblProducto
        final TextView lblIMenu = (TextView) view.findViewById(R.id.lblProducto_canti);
        lblIMenu.setText(String.format("%d %s", cantidadItem_Pedido, NombreItem_Pedido));

        //Botones
        final ImageButton Okbtn = (ImageButton) view.findViewById(R.id.imgbtnOK_canti);
        Okbtn.setOnClickListener(OnClickListener);
        final ImageButton Cancelbtn = (ImageButton) view.findViewById(R.id.imgbtnCancel_canti);
        Cancelbtn.setOnClickListener(OnClickListener);
        numPick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cantidadItem_Pedido = numPick.getValue();
            }
        });
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_circ_items);
        alertDialogBuilder.setTitle(R.string.kssCantidadItem);

        return alertDialogBuilder.create();
    }

    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgbtnOK_canti:
                    ((pedido) getActivity()).setCambiarCantidad(numPick.getValue());
                    dismiss();
                    break;
                case R.id.imgbtnCancel_canti:
                    //Realizar albores de Cancel
                    ToastManager.show(getActivity(), "No se realizaron cambios.", ToastManager.WARNING, 0);
                    dismiss();
                    break;
            }
        }
    };
}
