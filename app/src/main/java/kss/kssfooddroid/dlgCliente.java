package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import Tablas.cliente_tabla;
import kss.kssfooddroid.util.ToastManager;
import kss.kssfooddroid.util.clsUtil_Net;

/**
 * Created by HENRY on 16/02/2015.
 */
public class dlgCliente extends android.support.v4.app.DialogFragment {
    private static String IDCliente = "";
    private static Boolean esClienteEnc = false;
    ArrayList<String> dataDropDown = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    private static Button btnBuscar;
    protected Context context;
    private static AutoCompleteTextView txtBuscar_Cliente;

    private static TextView txtCIRIF_Cliente;
    private static TextView txtNombreCliente__Cliente;
    private static TextView txtCliente;
    private static TextView txtTelefeono_Cliente;

    private static TextView lblCIRIF_Cliente;
    private static TextView lblNombreCliente_Cliente;
    private static TextView lblDireccion_Cliente;
    private static Window window;
    private static TableLayout tblClienteAdd_Cliente;
    private static TableLayout tblClienteEnc_Cliente;
    private static RadioButton porRIF;

    public cliente_tabla ClienteSelected;
    private static List lstClientes = new ArrayList();

    public dlgCliente() {

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
            params.setTitle("Dialogo Clientes");
            window.setAttributes(params);
            // window.setBackgroundDrawableResource(android.R.color.transparent);

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_cliente, null);
        context = getActivity();
        //Botone
        final ImageButton Okbtn = (ImageButton) view.findViewById(R.id.imgbtnOK_Cliente);
        Okbtn.setOnClickListener(OnClickListener);
        final ImageButton Cancelbtn = (ImageButton) view.findViewById(R.id.imgbtnCancel_Cliente);
        Cancelbtn.setOnClickListener(OnClickListener);
        btnBuscar = (Button) view.findViewById(R.id.btnbuscarCliente_dlgCliente);
        btnBuscar.setOnClickListener(OnClickListener);

        txtBuscar_Cliente = (AutoCompleteTextView) view.findViewById(R.id.txtBuscar_Cliente);
        txtCIRIF_Cliente = (TextView) view.findViewById(R.id.txtCIRIF_Cliente);
        txtNombreCliente__Cliente = (TextView) view.findViewById(R.id.txtNombreCliente__Cliente);
        txtCliente = (TextView) view.findViewById(R.id.txtDireccion_Cliente);
        txtTelefeono_Cliente = (TextView) view.findViewById(R.id.txtTelefeono_Cliente);

        lblCIRIF_Cliente = (TextView) view.findViewById(R.id.lblCIRIF_Cliente);
        lblNombreCliente_Cliente = (TextView) view.findViewById(R.id.lblNombreCliente_Cliente);
        lblDireccion_Cliente = (TextView) view.findViewById(R.id.lblDireccion_Cliente);

        porRIF= (RadioButton) view.findViewById(R.id.rdClientePorRIF);
        porRIF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    txtBuscar_Cliente.setHint("Indique el RIF");
                }else{
                    txtBuscar_Cliente.setHint("Indique el Nombre");
                }
                if (adapter!=null){
                    adapter.clear();
                }
                if (dataDropDown!=null){
                    dataDropDown.clear();
                }
                if (lstClientes!=null){
                    lstClientes.clear();
                }
                txtBuscar_Cliente.clearFocus();
                txtBuscar_Cliente.setText("");
            }
        });


        // ocultar Edicion de clientes
        tblClienteAdd_Cliente = (TableLayout) view.findViewById(R.id.tblClienteAdd_Cliente); // Seleccionar y mostrar el layout a mostrar
        tblClienteAdd_Cliente.setVisibility(View.GONE); //Invisible y si apartar espacio
        // ocultar Edicion de clientes
        tblClienteEnc_Cliente = (TableLayout) view.findViewById(R.id.tblClienteEnc_Cliente); // Seleccionar y mostrar el layout a mostrar
        tblClienteEnc_Cliente.setVisibility(View.GONE); //Invisible y si apartar espacio

        // Crear AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_cliente);
        alertDialogBuilder.setTitle(R.string.kssSeleccionaCliente);

        txtBuscar_Cliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtBuscar_Cliente.selectAll();
                ClienteSelected = (cliente_tabla) lstClientes.get(position);
                MostrarDatosCliente(ClienteSelected);
                if (ClienteSelected != null) {
                    ToastManager.show(context, "Ha seleccionado: \n" + ClienteSelected.nombre.toString(), ToastManager.INFORMATION, 0);
                }
            }
        });
        txtBuscar_Cliente.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                txtBuscar_Cliente.selectAll();
                txtBuscar_Cliente.showDropDown();
                return false;
            }
        });
        if (ClienteSelected!=null){
            MostrarDatosCliente(ClienteSelected);
        }

        return alertDialogBuilder.create();
    }

public void MostrarDatosCliente(cliente_tabla Cliente){
    if (Cliente!=null) {
        if (ClienteSelected==null){
            ClienteSelected= Cliente;
        }
        lblCIRIF_Cliente.setText(ClienteSelected.rif);
        lblNombreCliente_Cliente.setText(ClienteSelected.nombre);
        lblDireccion_Cliente.setText(ClienteSelected.direccion);
        tblClienteEnc_Cliente.setVisibility(View.VISIBLE);
    } else {
        lblCIRIF_Cliente.setText("");
        lblNombreCliente_Cliente.setText("");
        lblDireccion_Cliente.setText("");
        tblClienteEnc_Cliente.setVisibility(View.INVISIBLE);
    }
}
    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnbuscarCliente_dlgCliente:
                    String sBuscar = txtBuscar_Cliente.getText().toString().trim();
                    if (sBuscar.length() > 0) {
                        cargardatosUsuarios(sBuscar);
                    } else {
                        ToastManager.show(context, "Debe indicar el texto a buscar :-/", ToastManager.INFORMATION, 1);
                    }
                    break;
                case R.id.imgbtnOK_Cliente:
                    switch (getActivity().getComponentName().getClassName()) {
                        case "kss.kssfooddroid.pedido":
                            ((pedido) getActivity()).setClienteSeleccionado(ClienteSelected);
                            break;
                        case "kss.kssfooddroid.mesas":
                            ((mesas) getActivity()).IDCliente = IDCliente;
                            break;
                    }
                    dismiss();
                    break;
                case R.id.imgbtnCancel_Cliente:
                    //Realizar albores de Cancel
                    ToastManager.show(getActivity(), "No ha Seleccionado un cliente", ToastManager.WARNING, 0);
                    switch (getActivity().getComponentName().getClassName()) {
                        case "kss.kssfooddroid.pedido":
                            ClienteSelected=null;
                            ((pedido) getActivity()).setClienteSeleccionado(null);
                            break;
                        case "kss.kssfooddroid.mesas":
                            ((mesas) getActivity()).IDCliente = IDCliente;
                            break;
                    }
                    dismiss();
                    break;
            }
        }
    };

    private void cargardatosUsuarios(String sBuscar) {

        //Mostrar lista usuarios
        dataDropDown = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(context,
                android.R.layout.select_dialog_item , dataDropDown);
        txtBuscar_Cliente.setAdapter(adapter);
        if (clsUtil_Net.checkWifiState(context)) {
            new AsyncLoadClientes().execute(sBuscar.toUpperCase());
        }

    }

    protected class AsyncLoadClientes extends AsyncTask<String, JSONObject, ArrayList<cliente_tabla>>
            //AsyncTask<String,Void,cliente_tabla>
    {
        private String sAccion = "Cargando datos...";
        private ProgressDialog dialogo;

        public AsyncLoadClientes() {
        }

        @Override
        protected void onPreExecute() {
            dialogo = new ProgressDialog(context);
            dialogo.setIcon(R.drawable.ic_launcher);
            dialogo.setMessage(sAccion);
            dialogo.setIndeterminate(false);
            dialogo.setCancelable(false);
            dialogo.show();
            dialogo.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    AsyncLoadClientes.this.cancel(true);
                }
            });
        }

        @Override
        protected void onCancelled() {
            dialogo.dismiss();
        }

        @Override
        protected ArrayList<cliente_tabla> doInBackground(String... params) {
            // hacer validacion si devuelve Error o se ejecuta
            // cancel(esWSActivo = clsUtil_Net.esWSActivo(context, RestAPI.urlString));
            ArrayList<cliente_tabla> cliente = null;
            RestAPI api = new RestAPI(context);
            try {
                sAccion = "Buscando cliente que contenga [" + params[0].toString().toUpperCase() + "]" +
                        "\n Esto puede tardar varios segundos :-}";
                publishProgress();
                JSONObject jsonObj ;
                if (porRIF.isChecked()){
                    jsonObj=api.getclientesPorRIF(params[0]);
                }else {
                   jsonObj= api.getclientesPorNombre(params[0]);
                }
                JSONParser parser = new JSONParser();
                cliente = parser.parseCliente_array(jsonObj);
                sAccion = "Se encontraron: " + String.valueOf(cliente.size()) + " Registros.";
                publishProgress();
                Thread.sleep((long) (200));
                return cliente;

            } catch (Exception e) {
                sAccion = "Algo ha salido mal :-/ \n" + e.getLocalizedMessage();
                publishProgress();
                try {
                    Thread.sleep((long) (2000));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                cancel(true);
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {

            dialogo.setMessage(sAccion);
        }

        @Override
        protected void onPostExecute(ArrayList<cliente_tabla> cliente) {
            try {
                if (cliente != null && cliente.size()!=0) {
                    //Cambiar Dialogo
                    lstClientes.clear();
                    window.setTitle("Registros");
                    for (int i = 0; i < cliente.size(); i++) {
                        lstClientes.add(new cliente_tabla(
                                cliente.get(i).rif,
                                cliente.get(i).nombre,
                                cliente.get(i).codigo,
                                cliente.get(i).ciudad,
                                cliente.get(i).telefono,
                                cliente.get(i).direccion
                        ));

                        if (porRIF.isChecked()){
                            dataDropDown.add(String.format("%s %s",
                                    String.valueOf(cliente.get(i).rif).trim(),
                                    String.valueOf(cliente.get(i).nombre).trim())) ;
                        }else {
                            dataDropDown.add(String.format("%s %s",
                                    String.valueOf(cliente.get(i).nombre).trim(),
                                    String.valueOf(cliente.get(i).rif).trim()));
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Thread.sleep(200);
                    txtBuscar_Cliente.showDropDown();
                    txtBuscar_Cliente.selectAll();
                } else {
                    //Ocurrio Error
                    if (cliente.size() == 0) {
                        ToastManager.show(context, "No se localizaron datos :-/", ToastManager.WARNING, 0);
                    } else {
                        ToastManager.show(context, "No se localizaron datos. Revise la conexión al servidor :-/", ToastManager.WARNING, 0);
                    }
                    cancel(true);
                }
            } catch (Exception ex) {
                ToastManager.show(context, ex.getMessage().toString(), ToastManager.ERROR, 0);
                cancel(true);
            }
            dialogo.dismiss();

        }
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public void updateAdapter(ArrayAdapter arrayAdapter, ArrayList<Object> listOfObject) {
        arrayAdapter.clear();
        for (Object object : listOfObject) {
            arrayAdapter.add(object);
        }
    }

}
