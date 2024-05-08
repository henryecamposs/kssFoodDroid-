package kss.kssfooddroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import Tablas.JSONParser;
import Tablas.RestAPI;
import Tablas.cliente_tabla;
import kss.kssfooddroid.util.clsUtil_Net;
import kss.kssfooddroid.util.enuSeleccion_Devolucion;
import kss.kssfooddroid.util.enuSeleccion_OpcMesa;
import kss.kssfooddroid.util.enuSeleccion_OpcPedido;

public class prueba_DlgVarios extends FragmentActivity implements AdapterView.OnItemClickListener {
    TextView txtProd;
    TextView lblResult;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    TextView txtBuscar;
    private static TextView lblRegistros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_dlg_varios);
        /*
        Agregamos los listener a los botones
        para abrir los dialogs.
        */
        context = this;
        Button abreDialog1 = (Button) findViewById(R.id.btnMostrarDLG1);
        abreDialog1.setOnClickListener(OnClickListener);
        Button abreDialog2 = (Button) findViewById(R.id.btnMostrarDLG2);
        abreDialog2.setOnClickListener(OnClickListener);
        Button abreDialog3 = (Button) findViewById(R.id.btnCliente);
        abreDialog3.setOnClickListener(OnClickListener);
        Button abreDialog4 = (Button) findViewById(R.id.btnItems);
        abreDialog4.setOnClickListener(OnClickListener);
        Button abreDialog5 = (Button) findViewById(R.id.btnOpc_Mesas);
        abreDialog5.setOnClickListener(OnClickListener);
        Button abreDialog6 = (Button) findViewById(R.id.btnOpcPedidos);
        abreDialog6.setOnClickListener(OnClickListener);
        Button abreDialog7 = (Button) findViewById(R.id.btnDevol);
        abreDialog7.setOnClickListener(OnClickListener);
        Button abreDialog8 = (Button) findViewById(R.id.btncanti);
        abreDialog8.setOnClickListener(OnClickListener);
        Button abreDialog9 = (Button) findViewById(R.id.btnPass);
        abreDialog9.setOnClickListener(OnClickListener);

        Button btnbuscar = (Button) findViewById(R.id.btnBuscar);
        btnbuscar.setOnClickListener(OnClickListener);
        txtBuscar = (TextView) findViewById(R.id.txtBuscar);
        lblRegistros = (TextView) findViewById(R.id.lblRegistros);

        listv = (ListView) findViewById(R.id.lstresult);
        listv.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) this);
        lblResult = (TextView) findViewById(R.id.lblItemSelected);
        cargardatosUsuarios("HENRY");


    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        String Item = adapter.getItemAtPosition(position).toString();
        lblResult.setText(Item);
    }



    private void cargardatosUsuarios(String sBuscar) {
//
        //Mostrar lista usuarios
        data = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);
        listv.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) this);
        if (clsUtil_Net.checkWifiState(context)) {
            new AsyncLoadClientes().execute(sBuscar.toUpperCase());
        }

    }

    protected class AsyncLoadClientes extends AsyncTask<String, JSONObject, ArrayList<cliente_tabla>>
            //AsyncTask<String,Void,cliente_tabla>
    {
        private String sAccion = "Cargando datos...";
        private ProgressDialog dialogo;

        @Override
        protected void onPreExecute() {
            dialogo = new ProgressDialog(prueba_DlgVarios.this);
            dialogo.setMessage(sAccion);
            dialogo.setIcon(R.drawable.ic_launcher);
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
        protected ArrayList<cliente_tabla> doInBackground(String... params) {
            // hacer validacion si devuelve Error o se ejecuta
            ArrayList<cliente_tabla> cliente = null;
            RestAPI api = new RestAPI(context);
            try {
                sAccion = "Conectando con Servidor...";
                publishProgress();
                JSONObject jsonObj = api.getclientesPorNombre(params[0]);
                JSONParser parser = new JSONParser();
                cliente = parser.parseCliente_array(jsonObj);
            } catch (Exception e) {
                try {
                    sAccion = "Algo ha salido MAL :-( \n" + e.getLocalizedMessage();
                    publishProgress();
                    Thread.sleep((long) (1000));
                    cancel(true);
                    return null;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    cancel(true);
                    return null;
                }
            }
            return cliente;
        }

        @Override
        protected void onCancelled() {
            dialogo.dismiss();
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            dialogo.setMessage(sAccion);
        }

        @Override
        protected void onPostExecute(ArrayList<cliente_tabla> cliente) {
            if (cliente != null) {
                for (int i = 0; i < cliente.size(); i++) {
                    data.add(cliente.get(i).rif.trim() + " " + cliente.get(i).nombre.trim());
                }
                adapter.notifyDataSetChanged();
                if (cliente.size() > 0) {
                    lblResult.setText(cliente.get(0).nombre.trim() + "-" + cliente.get(0).rif.trim());
                } else {
                    lblResult.setText("No existen datos que mostrar");
                }
                lblRegistros.setText("Registros: " + String.valueOf(cliente.size()));
            } else {
                //Ocurrio Error
                cancel(true);
            }
            dialogo.dismiss();
        }
    }

    private OnClickListener OnClickListener = new OnClickListener() {
        @Override
        public void onClick(final View v) {
            Bundle datosActuales = new Bundle();
            try {
                switch (v.getId()) {
                    case R.id.btnBuscar:
                        cargardatosUsuarios(txtBuscar.getText().toString());
                        break;
                    case R.id.btnCliente:
                        FragmentManager fm3 = getSupportFragmentManager();
                        dlgCliente nuevoDialog3 = new dlgCliente();
                        nuevoDialog3.show(fm3, "dialog3");
                        break;
                    case R.id.btnItems:
                        FragmentManager fm4 = getSupportFragmentManager();
                        dlgItemMenuBuscar nuevoDialog4 = new dlgItemMenuBuscar();
                        nuevoDialog4.show(fm4, "dialog4");
                        break;
                    case R.id.btnOpc_Mesas:
                        FragmentManager fm5 = getSupportFragmentManager();
                        dlgMesaOpciones nuevoDialog5 = new dlgMesaOpciones();
                        nuevoDialog5.show(fm5, "dialog5");
                        break;
                    case R.id.btnOpcPedidos:
                        FragmentManager fm6 = getSupportFragmentManager();
                        dlgPedidoOpciones nuevoDialog6 = new dlgPedidoOpciones();
                        nuevoDialog6.show(fm6, "dialog6");
                        break;
                    case R.id.btnDevol:
                        datosActuales.putString("Producto", txtProd.getText().toString());
                        FragmentManager fm8 = getSupportFragmentManager();
                        dlgPedidoDevolucion nuevoDialog8 = new dlgPedidoDevolucion();
                        nuevoDialog8.setArguments(datosActuales);
                        nuevoDialog8.show(fm8, "dialog8");
                        break;
                    case R.id.btncanti:
                        datosActuales.putString("Producto", txtProd.getText().toString());
                        FragmentManager fm7 = getSupportFragmentManager();
                        dlgPedidoCantidad nuevoDialog7 = new dlgPedidoCantidad();
                        nuevoDialog7.setArguments(datosActuales);
                        nuevoDialog7.show(fm7, "dialog7");
                        break;
                    case R.id.btnPass:
                        FragmentManager fm9 = getSupportFragmentManager();
                        dlgUsuarioPass nuevoDialog9 = new dlgUsuarioPass();
                        nuevoDialog9.show(fm9, "dialog9");
                        break;
                }
//            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(txtProd.getWindowToken(), 0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void UsuerioPass(String Pass) {
        lblResult.setText("Selecciono " + Pass);

    }

    public void DatosIMenu(String IDIMenu) {
        lblResult.setText("Selecciono " + IDIMenu);

    }

    public void DatosCliente(String IDCliente) {
        lblResult.setText("Selecciono " + IDCliente);

    }

    public void pedidoCantidad(int Canti) {
        lblResult.setText("Selecciono " + Canti);

    }

    public void opcMesa(enuSeleccion_OpcMesa enuSeleccion) {
        switch (enuSeleccion) {
            case IMPRIMIR_CUENTA:
                lblResult.setText("Selecciono IMPRIMIR_CUENTA");
                break;
            case MOSTRAR_CUENTA:
                lblResult.setText("Selecciono MOSTRAR_CUENTA");
                break;
            case CLIENTE:
                lblResult.setText("Selecciono CLIENTE");
                break;
            case OPCIONES:
                lblResult.setText("Selecciono OPCIONES ");
                break;

        }
    }

    public void opcPedido(enuSeleccion_OpcPedido enuSeleccion) {
        switch (enuSeleccion) {
            case IMPRIMIR_COMANDA:
                lblResult.setText("Selecciono IMPRIMIR_COMANDA");
                break;
            case MOSTRAR_CUENTA:
                lblResult.setText("Selecciono MOSTRAR_CUENTA");
                break;
            case CLIENTE:
                lblResult.setText("Selecciono CLIENTE");
                break;
            case OPCIONES:
                lblResult.setText("Selecciono OPCIONES ");
                break;

        }
    }

    public void pedidoDevolucion(enuSeleccion_Devolucion opcion, int cantidad) {
        switch (opcion) {
            case ELIMINAR_ITEM:
                lblResult.setText("Selecciono Eliminar ITEM");
                break;
            case ELIMINAR_TODO:
                lblResult.setText("Selecciono Eliminar Todo");
                break;
            case REDUCIR_ITEM:
                lblResult.setText("Selecciono Reducir " + String.valueOf(cantidad));
                break;

        }
    }

    public void actualizarNombre(String nombre) {
        // nombre, contiene el nombre ingresado por el usuario
        TextView txtv = (TextView) findViewById(R.id.textView2);
        if (nombre.length() < 1) {
            // No queremos anónimos, el usuario no ingresó
            // su nombre o apretó cancelar
            txtv.setText("Cobarde ... ingrese su nombre");
        } else {
            txtv.setText(nombre);
        }
    }


    //Listener TextView
   /* txtCanti.addTextChangedListener(new TextWatcher(){
        public void afterTextChanged(Editable s) {}
    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
    public void onTextChanged(CharSequence s, int start, int before, int count){
        if(txtCanti.getText().toString().length()>0){
            cantidadItem_Pedido= (parseInt(txtCanti.getText().toString()));
        }

    }
    });*/
}