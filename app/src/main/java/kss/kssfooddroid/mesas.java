package kss.kssfooddroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Tablas.JSONParser;
import Tablas.RestAPI;
import Tablas.ambienteDetalle_tabla;
import Tablas.ambientes_tabla;
import kss.kssfooddroid.util.ToastManager;
import kss.kssfooddroid.util.clsUtil_Net;
import kss.kssfooddroid.util.enuEstadoMesas;
import kss.kssfooddroid.util.enuSeleccion_OpcMesa;


public class mesas extends FragmentActivity {
    public static String IDCliente = "";
    private static String IDPedido = "";
    private static String IDMesa = "";
    private static String IDEmpleado = "";
    private static String IDEmpleadoNivel = "";
    private static String NombreMesa = "";
    private static String NombreEmpleado = "";
    private static String IDAmbiente = "";
    private static String NombreAmbiente = "";

    public static enuSeleccion_OpcMesa _enuSeleccion_OpcMesa = enuSeleccion_OpcMesa.NO_SELECCION;


    private static Context context;
    private static enuEstadoMesas enEstadoMesas = enuEstadoMesas.VACIA;
    private static Button[] btnMesas = new Button[42];
    private static Button[] btnAmbientes = new Button[6];
    private int totalAmbientes;
    private int totalMesas;
    private ArrayList<ambienteDetalle_tabla> aAmbienteDetalle=null;
    private ArrayList<ambientes_tabla> aAmbientes=null;
    private static ambientes_tabla ambienteSelected;
    private static ambienteDetalle_tabla MesaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultar barra Notificacion
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_mesas);
        context = this;
        //Ocultar botones Mesa y Ambientes
        final Button btnMenuOpc_mesas = (Button) findViewById(R.id.btnMenuOpc_mesas);
        btnMenuOpc_mesas.setOnClickListener(OnClickListener);
        final ImageButton btnimgHelp_mesas = (ImageButton) findViewById(R.id.btnimgHelp_mesas);
        btnimgHelp_mesas.setOnClickListener(OnClickListener);
        llenarArrayAmbientes();
    }

    private void llenarArrayAmbientes() {
        aAmbientes= new ArrayList<ambientes_tabla>();
        if (clsUtil_Net.checkWifiState(context)) {
            new AsyncLoadAmbientes().execute();
        }
    }

    private void llenarArrayMesas(String sIDAmbiente, String sNombreAmbiente, String sNumAmbiente) { //Cargar datos BD
        aAmbienteDetalle = new ArrayList<ambienteDetalle_tabla>();
       //TODO:(tablet conectada) Activar comprobacion
       // if (clsUtil_Net.checkWifiState(context)) {
            new AsyncLoadAmbienteDetalle().execute(sIDAmbiente,sNombreAmbiente, sNumAmbiente);
       // }
    }

    /**
     * Obtener los datos de la BD MESAS
     */
    protected class AsyncLoadAmbienteDetalle extends AsyncTask<String, JSONObject, ArrayList<ambienteDetalle_tabla>>
            //AsyncTask<String,Void,cliente_tabla>
    {
        private String sAccion = "Cargando datos...";
        private ProgressDialog dialogo;

        @Override
        protected void onPreExecute() {
            dialogo = new ProgressDialog(context);
            dialogo.setMessage(sAccion);
            dialogo.setIcon(R.drawable.ic_launcher);
            dialogo.setIndeterminate(false);
            dialogo.setCancelable(false);
            dialogo.show();
            dialogo.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    AsyncLoadAmbienteDetalle.this.cancel(true);
                }
            });
        }

        @Override
        protected ArrayList<ambienteDetalle_tabla> doInBackground(String... params) {
            // hacer validacion si devuelve Error o se ejecuta
            // cancel(esWSActivo = clsUtil_Net.esWSActivo(context, RestAPI.urlString));
            ArrayList<ambienteDetalle_tabla> AmbienteDetalle = null;
            RestAPI api = new RestAPI(context);
            try {
                sAccion = "Cargando mesas del ambiente " + params[1].toString().trim().toUpperCase()  +
                        "\n Esto puede tardar varios segundos :-}";
                publishProgress();
                final int numAmbiente= Integer.parseInt(params[2])+1;
                JSONObject jsonObj = api.getMesas("ms"+numAmbiente);
                JSONParser parser = new JSONParser();
                AmbienteDetalle = parser.parserAmbienteDetalle_array(jsonObj);
                return AmbienteDetalle;
            } catch (final Exception e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastManager.show(context, "Algo ha salido mal :-( \n" + e.getLocalizedMessage().toString(), ToastManager.ERROR, 0);
                    }
                });
                cancel(true);
                return null;
            }
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
        protected void onPostExecute(ArrayList<ambienteDetalle_tabla> AmbienteDetalle) {
            if (AmbienteDetalle != null && AmbienteDetalle.size()>0) {
                aAmbienteDetalle=AmbienteDetalle;
                final Drawable imgbtnEsImpreso = getResources().getDrawable(R.drawable.btn_cuentaprint);
                final Drawable imgbtnMesaOcupada = getResources().getDrawable(R.drawable.btn_mesa_out);
                final Drawable imgbtnMesaDividida = getResources().getDrawable(R.drawable.btn_mesa_dividir);
                final Drawable imgbtnTiempoImpresa = getResources().getDrawable(R.drawable.btn_reloj);
                final Drawable imgbtnMesaVacia = getResources().getDrawable(R.drawable.ksslogo_opaco);
                totalMesas = 0;
                try {
                    totalMesas = 0;
                    for (int iMesa = 0; iMesa < 42; iMesa++) {
                        ambienteDetalle_tabla Mesa;
                        Mesa = AmbienteDetalle.get(iMesa);
                        String btnNombre = "btnMesa" + iMesa + "_mesas";
                        int resID = getResources().getIdentifier(btnNombre, "id", getPackageName());
                        btnMesas[iMesa] = (Button) findViewById(resID);
                        btnMesas[iMesa].setTag(iMesa);
                        if (Mesa.mesa.trim().length() > 0) {
                            totalMesas++;
                            btnMesas[iMesa].setText(Mesa.mesa.trim().toUpperCase() +" Bs." +String.format("%.2f",Mesa.monto));
                            btnMesas[iMesa].setVisibility(View.VISIBLE);
                            if (Mesa.abierta.equals("S") ) {
                                enEstadoMesas = enuEstadoMesas.OCUPADA;
                            }else{
                                enEstadoMesas = enuEstadoMesas.VACIA;
                            }
                            //mesa Impresa
                            if (Mesa.impreso == 1) {
                                enEstadoMesas = enuEstadoMesas.IMPRESA;
                            }
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                            //Tiempo impreso
                            //final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
                            final Date FechaAnterior, FechaFinal;
                            try {
                                FechaAnterior = formatter.parse(Mesa.horaulti);
                                FechaFinal = new Date();
                                long diferencia = FechaFinal.getTime() - FechaAnterior.getTime();//MILLSECS_PER_DAY
                                if (diferencia > 1) {
                                    enEstadoMesas = enuEstadoMesas.TIEMPO_IMPRESA;
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            switch (enEstadoMesas) {
                                case OCUPADA:
                                    btnMesas[iMesa].setCompoundDrawablesWithIntrinsicBounds(null, imgbtnMesaOcupada, null, null);
                                    break;
                                case VACIA:
                                    btnMesas[iMesa].setCompoundDrawablesWithIntrinsicBounds(null, imgbtnMesaVacia, null, null);
                                    break;
                                case IMPRESA:
                                    btnMesas[iMesa].setCompoundDrawablesWithIntrinsicBounds(null, imgbtnEsImpreso, null, null);
                                    break;
                                case TIEMPO_IMPRESA:
                                    btnMesas[iMesa].setCompoundDrawablesWithIntrinsicBounds(null, imgbtnTiempoImpresa, null, null);
                                    break;
                                case DIVIDIDA:
                                    btnMesas[iMesa].setCompoundDrawablesWithIntrinsicBounds(null, imgbtnMesaDividida, null, null);
                                    break;
                            }

                        } else {
                            btnMesas[iMesa].setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(context, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                //Mostrar mensaje no existen Ambientes
                ToastManager.show(context, "No existen Mesas configurados para este Ambiente.", ToastManager.INFORMATION, 0);
                cancel(true);
            }
            dialogo.dismiss();
        }
    }


    /**
     * Obtener los datos de la BD AMBIENTES
     */
    protected class AsyncLoadAmbientes extends AsyncTask<String, JSONObject, ArrayList<ambientes_tabla>>

    {
        private String sAccion = "Cargando datos...";
        private ProgressDialog dialogo;

        @Override
        protected void onPreExecute() {
            dialogo = new ProgressDialog(context);
            dialogo.setMessage(sAccion);
            dialogo.setIcon(R.drawable.ic_launcher);
            dialogo.setIndeterminate(false);
            dialogo.setCancelable(false);
            dialogo.show();

        }

        @Override
        protected ArrayList<ambientes_tabla> doInBackground(String... params) {
            ArrayList<ambientes_tabla> Ambientes = null;
            RestAPI api = new RestAPI(context);
            try {
                sAccion = "Cargando Ambientes..." + "\n Esto puede tardar varios segundos :-}";
                publishProgress();
                JSONObject jsonObj = api.getAmbientes();
                JSONParser parser = new JSONParser();
                Ambientes = parser.parseAmbiente_array(jsonObj);
                sAccion = "Se encontraron: " + String.valueOf(Ambientes.size()) + " Registros.";
                publishProgress();
                Thread.sleep((long) (2000));
                return Ambientes;
            } catch (final Exception e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastManager.show(context, "Algo ha salido mal :-( \n" + e.getLocalizedMessage().toString(), ToastManager.ERROR, 0);
                    }
                });
                cancel(true);
                return null;
            }
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
        protected void onPostExecute(ArrayList<ambientes_tabla> Ambientes) {
            if (Ambientes != null && Ambientes.size()>0) {
                totalAmbientes = 0;
                aAmbientes=Ambientes;
                try {
                    for (int iAmbiente = 0; iAmbiente < 6; iAmbiente++) {
                        ambientes_tabla Ambiente;
                        Ambiente = Ambientes.get(iAmbiente);
                        String btnNombre = "btnAmbiente" + iAmbiente + "_mesas";
                        int resID = getResources().getIdentifier(btnNombre, "id", getPackageName());
                        btnAmbientes[iAmbiente] = (Button) findViewById(resID);
                        if (Ambiente.descr.length() > 0) {
                            totalAmbientes++;
                            btnAmbientes[iAmbiente].setText(Ambiente.descr.trim().toUpperCase());
                            btnAmbientes[iAmbiente].setVisibility(View.VISIBLE);
                            btnAmbientes[iAmbiente].setTag(iAmbiente);
                        } else {
                            btnAmbientes[iAmbiente].setVisibility(View.INVISIBLE);
                        }
                    }
                    //Cargar primer Ambiente
                    llenarArrayMesas(Ambientes.get(0).codigo,Ambientes.get(0).descr,String.valueOf(0));
                } catch (Exception e) {
                    Toast toast = Toast.makeText(context, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                //Mostrar mensaje no existen Ambientes
                ToastManager.show(context, "No existen Ambientes configurados para este sistema.\n La aplicación no podra continuar.", ToastManager.INFORMATION, 1);
                cancel(true);
            }
            dialogo.dismiss();
        }
    }

    public void kss_btnClick_ambiente(View btn) {
        //Cargar mesas segun IDAmbiente
        ambienteSelected = aAmbientes.get(Integer.valueOf(btn.getTag().toString()));
        IDAmbiente = ambienteSelected.codigo.trim();
        NombreAmbiente = ambienteSelected.descr.trim();
        llenarArrayMesas(IDAmbiente, NombreAmbiente,btn.getTag().toString());
    }

    public void kss_btnClick(View btn) {
        try {
            //Seleccionar la mesa adecuada
            MesaSelected = aAmbienteDetalle.get(Integer.valueOf(btn.getTag().toString()));
            IDMesa = String.valueOf(Integer.valueOf(btn.getTag().toString()));
            NombreMesa = MesaSelected.mesa.trim();
            IDCliente = MesaSelected.codcli.trim();
            IDEmpleado = MesaSelected.emple.trim();
            NombreEmpleado = MesaSelected.nomem.trim();
            //IDEmpleadoNivel del usuario que Introdujo la Clave

            //Mensaje decambio ambiente
            ToastManager.show(this, "Abriendo pedido (" + NombreMesa + ")...", ToastManager.INFORMATION, 0);
            //preparar parametros a pasar
            Intent i = new Intent(this, pedido.class);
            Bundle b = new Bundle();
            b.putString("IDMesa", IDMesa);
            b.putString("IDCliente", IDCliente);
            b.putString("IDPedido", IDPedido);
            b.putString("IDEmpleado", IDEmpleado);
            b.putString("IDEmpleadoNivel", IDEmpleadoNivel);
            b.putString("IDAmbiente", IDAmbiente);
            b.putString("NombreMesa", NombreMesa);
            b.putString("NombreEmpleado", NombreEmpleado);
            b.putString("NombreAmbiente", NombreAmbiente);
            i.putExtras(b);
            startActivity(i);
        } catch (final Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle datosActuales = new Bundle();
            switch (v.getId()) {
                case R.id.btnMenuOpc_mesas:
                    //Llamar dialogo menu pedido
                    datosActuales.putString("NombreMesa", NombreMesa);
                    FragmentManager fm6 = getSupportFragmentManager();
                    dlgMesaOpciones _dlgMesaOpciones = new dlgMesaOpciones();
                    _dlgMesaOpciones.setArguments(datosActuales);
                    _dlgMesaOpciones.show(fm6, "dlgMesaOpciones");
                    switch (_enuSeleccion_OpcMesa) {
                        case IMPRIMIR_CUENTA:
                            break;
                        case MOSTRAR_CUENTA:
                            break;
                        case CLIENTE:
                            break;
                        case OPCIONES:
                            break;
                        case NO_SELECCION:
                            break;
                    }

                    break;
                case R.id.btnimgHelp_mesas:
                    //Llamar dialogo Buscar producto
                    FragmentManager fm10 = getSupportFragmentManager();
                    dlgMesashelp _dlgMesashelp = new dlgMesashelp();
                    _dlgMesashelp.show(fm10, "dlgMesashelp");
                    break;
            }
        }
    };


}
