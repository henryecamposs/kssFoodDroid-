package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Tablas.JSONParser;
import Tablas.RestAPI;
import kss.kssfooddroid.util.ToastManager;
import kss.kssfooddroid.util.clsUtil_Net;


/**
 * Created by HENRY on 20/02/2015.
 */
public class dlgPedidoExtra extends android.support.v4.app.DialogFragment  {
    private static Window window;
    private List lstItemsPedido;

    public void dlgPedidoCantidad(){

    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = 700;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.setTitle("Extras pedido");
            window.setAttributes(params);
            // window.setBackgroundDrawableResource(android.R.color.transparent);

        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_pedido_extras, null);

        final String NombreItem_Pedido = getArguments().getString("NombreItem_Pedido");
        int cantidadItem_Pedido = getArguments().getInt("cantidadItem_Pedido");

        //Asignr Valor lblProducto
        final TextView lblIMenu=(TextView)  view.findViewById(R.id.lblProducto_pedidoExtra);
        lblIMenu.setText(String.format("%d %s", cantidadItem_Pedido, NombreItem_Pedido));

        //Botones
        final ImageButton Okbtn= (ImageButton) view.findViewById(R.id.imgbtnOK_pedidoExtra);
        Okbtn.setOnClickListener(OnClickListener);
        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_pedidoExtra);
        Cancelbtn.setOnClickListener(OnClickListener);

        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_circ_items);
        alertDialogBuilder.setTitle(R.string.kssPedidoExtra);

        return alertDialogBuilder.create();
    }
    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.imgbtnOK_pedidoExtra:
                    ((pedido) getActivity()).setReordenarItemspedido(lstItemsPedido);
                    dismiss();
                    break;
                case R.id.imgbtnCancel_pedidoExtra:
                    //Realizar labores de Cancel
                    ToastManager.show(getActivity(), "Cancelado", ToastManager.WARNING, 0);
                    dismiss();
                    break;
                case R.id.btnBuscar_PedidoExtra:
                    //Llamar dialogo Buscar producto
                    FragmentManager fm10 = getFragmentManager();
                    dlgPedidoExtra _dlgPedidoHelp = new dlgPedidoExtra();
                    _dlgPedidoHelp.show(fm10, "dlgPedidoHelp");
                    break;
                case R.id.btnMsjPerson_PedidoExtra:

                    break;

            }
        }
    };
    //region etodos Personalizados

    public void setReordenarItemspedido(List mlstItemsPedido ){
        lstItemsPedido=mlstItemsPedido;
    }

    //endregion
}
