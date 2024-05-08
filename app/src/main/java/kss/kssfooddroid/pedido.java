package kss.kssfooddroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Tablas.ItemMenu_Tabla;
import Tablas.JSONParser;
import Tablas.RestAPI;
import Tablas.cliente_tabla;
import Tablas.grupos_tabla;
import kss.kssfooddroid.util.ToastManager;
import kss.kssfooddroid.util.clsUtil_Net;
import kss.kssfooddroid.util.enuSeleccion_Devolucion;
import kss.kssfooddroid.util.enuSeleccion_OpcPedido;

import static kss.kssfooddroid.util.clsUtil_Utilidades.FechaActualToString;


public class pedido extends FragmentActivity {
    //region Definicion variables Globales y privadas
    public static String IDCliente = "";
    public static int IDItem = 0;

    public static String NombreItem_Pedido = "";


    public static enuSeleccion_OpcPedido _enuSeleccion_OpcPedido = enuSeleccion_OpcPedido.NO_SELECCION;

    private static String IDPedido = "";
    public static String IDMesa = "";
    public static String NombreMesa = "";

    public static String IDEmpleado = "";
    public static String IDEmpleadoNivel = "";
    public static String NombreEmpleado = "";

    private static Button btnCliente_Pedido;
    private static Context context;
    private static GridView gridvItemsMenu;
    private Button btnIMenuGrupos_Pedido;

    private static ListView lvItemsPedido;
    private static List lstItemsPedido = new ArrayList();
    private static List lstItemsMenu = new ArrayList();

    private static ItemPedido itemPedidoSelected;
    private static ItemMenu ItemMenuSelected;
    private static enuNivelMenu NivelActual = enuNivelMenu.NINGUNO;
    private static TextView lblTotal_Pedido;
    private static Drawable imgbtnCliente;
    private static Drawable imgbtnCliente_ON;
    private static cliente_tabla ClienteSelected;
    private static TextView lblCliente_Pedido;
    private Button btnDownlstpedido_pedido;
    private Button btnUplstpedido_pedido;


//endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Colocar activity sin barra de notificacion
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        setContentView(R.layout.activity_pedido);
        context = this;
//region Definicion de variables y eventos
        //Botones

        imgbtnCliente = getResources().getDrawable(R.drawable.btn_cliente);
        imgbtnCliente_ON = getResources().getDrawable(R.drawable.btn_cliente_on);

        final Button btnIMenuBuscar_Pedido = (Button) findViewById(R.id.btnIMenuBuscar_Pedido);
        final Button btnAtras_Pedido = (Button) findViewById(R.id.btnAtras_Pedido);
        final Button btnCliente_opcPedido = (Button) findViewById(R.id.btnMenu_Pedido);
        final Button btnItemCantidad_Pedido = (Button) findViewById(R.id.btnItemCantidad_Pedido);
        final Button btnItemEliminar_Pedido = (Button) findViewById(R.id.btnItemEliminar_Pedido);
        final Button btnItemExtra_Pedido = (Button) findViewById(R.id.btnItemExtra_Pedido);
        final ImageButton imgbtnHelp_Pedido = (ImageButton) findViewById(R.id.imgbtnHelp_Pedido);
        final TextView lblHora_pedido = (TextView) findViewById(R.id.lblHora_pedido);
        final TextView lblEmpleado_Pedido = (TextView) findViewById(R.id.lblEmpleado_Pedido);
        lblTotal_Pedido = (TextView) findViewById(R.id.lblTotal_Pedido);
        btnCliente_Pedido = (Button) findViewById(R.id.btnCliente_Pedido);
        btnIMenuGrupos_Pedido = (Button) findViewById(R.id.btnIMenuGrupos_Pedido);
        lblCliente_Pedido = (TextView) findViewById(R.id.lblCliente_pedido);
        lblCliente_Pedido.setVisibility(View.GONE);
        btnDownlstpedido_pedido = (Button) findViewById(R.id.btnDownlstpedido_pedido);
        btnUplstpedido_pedido = (Button) findViewById(R.id.btnUplstpedido_pedido);

        //Generar eventos a Botones
        btnDownlstpedido_pedido.setOnClickListener(OnClickListener);
        btnUplstpedido_pedido.setOnClickListener(OnClickListener);
        btnCliente_Pedido.setOnClickListener(OnClickListener);
        btnAtras_Pedido.setOnClickListener(OnClickListener);
        btnIMenuBuscar_Pedido.setOnClickListener(OnClickListener);
        btnIMenuGrupos_Pedido.setOnClickListener(OnClickListener);
        btnCliente_opcPedido.setOnClickListener(OnClickListener);
        btnItemCantidad_Pedido.setOnClickListener(OnClickListener);
        btnItemEliminar_Pedido.setOnClickListener(OnClickListener);
        btnItemExtra_Pedido.setOnClickListener(OnClickListener);
        imgbtnHelp_Pedido.setOnClickListener(OnClickListener);
        //endregion

        //Asignar Variables a views
        btnAtras_Pedido.setText(NombreMesa);
        lblHora_pedido.setText(FechaActualToString(""));
        lblEmpleado_Pedido.setText(NombreEmpleado);
        lblTotal_Pedido.setText(String.format("%,10.2f", 0.0));

        //obtener los parametros de ventana anterior
        obtenerParametros();

//region ListView Pedidos
        lvItemsPedido = (ListView) findViewById(R.id.lvPedido);
        lvItemsPedido.setAdapter(new ItemsPedidoAdapter(this, lstItemsPedido));
        lvItemsPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                try {
                    itemPedidoSelected = (ItemPedido) lvItemsPedido.getAdapter().getItem(position);
                    view.setSelected(true);
                } catch (Exception ex) {
                    ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
                }

            }
        });
        lvItemsPedido.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (totalItemCount > 0 && totalItemCount > visibleItemCount) {
                    //Mostrar botones
                    if (lvItemsPedido.getLastVisiblePosition() < totalItemCount
                            && firstVisibleItem == 0) {
                        btnUplstpedido_pedido.setVisibility(View.INVISIBLE);
                        btnDownlstpedido_pedido.setVisibility(View.VISIBLE);
                    } else if (firstVisibleItem > 0
                            && lvItemsPedido.getLastVisiblePosition() + 1 == totalItemCount) {
                        btnUplstpedido_pedido.setVisibility(View.VISIBLE);
                        btnDownlstpedido_pedido.setVisibility(View.INVISIBLE);
                    } else {
                        btnUplstpedido_pedido.setVisibility(View.VISIBLE);
                        btnDownlstpedido_pedido.setVisibility(View.VISIBLE);
                    }
                } else {
                    btnUplstpedido_pedido.setVisibility(View.INVISIBLE);
                    btnDownlstpedido_pedido.setVisibility(View.INVISIBLE);
                }
            }
        });


        //endregion

//region Grid Menu
        cargarDatosGruposItems();
        gridvItemsMenu = (GridView) findViewById(R.id.grdviewItemsMenu);
        gridvItemsMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ItemMenuSelected = (ItemMenu) gridvItemsMenu.getAdapter().getItem(position);
                    switch (NivelActual) {
                        case GRUPO:
                            //Cargar Items Menu
                            cargarDatosItemMenu(ItemMenuSelected.codigo);
                            break;
                        case SUBGRUPO:
                        case ITEM_SUBGRUPO:
                        case ITEM_GRUPO:
                            if (ItemMenuSelected.getEnuTipoItem() == enuNivelMenu.SUBGRUPO) {
                                //Cargar los Items de subgrupos
                                cargarDatosItemMenu(ItemMenuSelected.grupo, ItemMenuSelected.codigo);
                            } else {
                                //Agregar Item a pedido
                                AgregarItemsPedido();

                                ToastManager.show(context, String.format("%s\nBs. %,8.2f", ItemMenuSelected.descr, ItemMenuSelected.precio), ToastManager.ADD, 0);
                                //Salir de Pantalla
                                SalirAGrupos();
                            }
                            break;
                    }
                } catch (Exception ex) {
                    ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
                }
            }
        });
        //endregion
    }

    //region Metodos personalizados


    private void AlAbrir_pedidos() {

    }

    private void AlCerrar_pedidos() {

    }
    public void setReordenarItemspedido(List mlstItemsPedido ){
        lstItemsPedido=mlstItemsPedido;
    }
    /**
     * Obtiene los datos del activity anterior
     */
    private void obtenerParametros() {

        //Obtengo la información de la Actividad anterior y se la asigno a la variable info.
        if (this.getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            bundle = this.getIntent().getExtras();
            if (bundle.getString("IDCliente") != null && !bundle.getString("IDCliente").equals("")) {
                IDCliente = bundle.getString("IDCliente");
                if (IDCliente.length() > 0) {
                    lblCliente_Pedido.setVisibility(View.VISIBLE);
                    lblCliente_Pedido.setText("Hay Un Cliente"); //TODO: Colocar el nombre Cliente
                    //TODO:Buscar CLiente
                    btnCliente_Pedido.setCompoundDrawablesWithIntrinsicBounds(null, imgbtnCliente_ON, null, null);
                } else {
                    lblCliente_Pedido.setVisibility(View.GONE);
                    btnCliente_Pedido.setCompoundDrawablesWithIntrinsicBounds(null, imgbtnCliente, null, null);
                }
            }
            if (bundle.getString("IDMesa") != null && !bundle.getString("IDMesa").equals("")) {
                IDMesa = bundle.getString("IDMesa");
            }
            if (bundle.getString("IDPedido") != null && !bundle.getString("IDCliente").equals("")) {
                IDPedido = bundle.getString("IDPedido");
            }
            if (bundle.getString("IDEmpleado") != null && !bundle.getString("IDEmpleado").equals("")) {
                IDEmpleado = bundle.getString("IDEmpleado");
            }
            if (bundle.getString("IDEmpleadoNivel") != null && !bundle.getString("IDEmpleadoNivel").equals("")) {
                IDEmpleadoNivel = bundle.getString("IDEmpleadoNivel");
            }
            if (bundle.getString("NombreMesa") != null && !bundle.getString("NombreMesa").equals("")) {
                NombreMesa = bundle.getString("NombreMesa");
            }
            if (bundle.getString("NombreEmpleado") != null && !bundle.getString("NombreEmpleado").equals("")) {
                NombreEmpleado = bundle.getString("NombreEmpleado");
            }
        }
    }

    /**
     * ReLlenar Items Pedido
     *
     * @return True si se existen Items
     */
    private boolean RellenarListVPedido() {
        return RellenarListVPedido(false);
    }

    private boolean RellenarListVPedido(boolean esNuevo) {
        try {
            lvItemsPedido.setAdapter(new ItemsPedidoAdapter(context, lstItemsPedido));
            int itemPosicion = 0;
            if (lstItemsPedido != null) {
                if (lstItemsPedido.size() > 0) {
                    if (esNuevo) {
                        //Seleccionar ultimo ITEM
                        itemPosicion = lstItemsPedido.size() - 1;
                        lvItemsPedido.setItemChecked(lstItemsPedido.size() - 1, true);
                        itemPedidoSelected = (ItemPedido) lstItemsPedido.get(itemPosicion);
                    } else {
                        itemPosicion = itemPedidoSelected.IDItem;
                        lvItemsPedido.setItemChecked(itemPosicion, true);
                        View mItem = (View) lvItemsPedido.getSelectedItem();
                        lvItemsPedido.smoothScrollToPosition(itemPosicion);
                    }
                    lvItemsPedido.setSelected(true);
                    // Centrar Item en pantalla
                    // Hacer Calculos
                    Double MontoTotalItemsPedido = 0.0;
                    for (int i = 0; i < lstItemsPedido.size(); i++) {
                        ItemPedido item = (ItemPedido) lstItemsPedido.get(i);
                        MontoTotalItemsPedido += item.getMontoTotal();
                    }
                    lblTotal_Pedido.setText(String.format("%,10.2f", MontoTotalItemsPedido));
                    return true;
                }
            }
        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString(), ToastManager.ERROR, 1);
        }
        return false;
    }

    public void setClienteSeleccionado(cliente_tabla mCliente) {
        if (mCliente != null) {
            ClienteSelected = mCliente;
            IDCliente = ClienteSelected.codigo;
            btnCliente_Pedido.setCompoundDrawablesWithIntrinsicBounds(null, imgbtnCliente_ON, null, null);
            lblCliente_Pedido.setVisibility(View.VISIBLE);
            lblCliente_Pedido.setText(ClienteSelected.nombre.trim());
        } else {
            btnCliente_Pedido.setCompoundDrawablesWithIntrinsicBounds(null, imgbtnCliente, null, null);
            lblCliente_Pedido.setVisibility(View.GONE);
            lblCliente_Pedido.setText("");
        }
    }

    /**
     * Cambiar Cantidades de ventana emergente
     *
     * @param Cantidad
     */
    public void setCambiarCantidad(int Cantidad) {
        //Cambiar valor original
        itemPedidoSelected.cantidad = Cantidad;
        lstItemsPedido.set(itemPedidoSelected.IDItem, itemPedidoSelected);
        RellenarListVPedido();
    }

    /**
     * Item encontrado en el Buscador
     *
     * @param ItemEncontrado
     */
    public void setItemEncontrado(ItemMenu ItemEncontrado) {
        if (ItemEncontrado != null) {
            ItemMenuSelected = ItemEncontrado;
            AgregarItemsPedido();
        }
    }

    public void setEliminarItem(int CantidadReduccion) {
        //Reduccion
        itemPedidoSelected.cantidad = CantidadReduccion;
        setEliminarItem(enuSeleccion_Devolucion.REDUCIR_ITEM);
    }

    public void setEliminarItem(enuSeleccion_Devolucion enuSeleccionDevolucion) {

        switch (enuSeleccionDevolucion) {
            case ELIMINAR_ITEM:
                itemPedidoSelected.cantidad = 0;
                break;
            case ELIMINAR_TODO:
                // Colocar todos los valores en Cero
                for (int i = 0; i < lstItemsPedido.size(); i++) {
                    ItemPedido Item = (ItemPedido) lstItemsPedido.get(i);
                    Item.cantidad = 0;
                    lstItemsPedido.set(Item.IDItem, Item);
                }
                break;
            case REDUCIR_ITEM:
                //Revisar la cantidad

                break;
            case NO_SELECCION:
                break;
        }
        lstItemsPedido.set(itemPedidoSelected.IDItem, itemPedidoSelected);
        RellenarListVPedido();
    }

    /**
     * Salir de la Pantalla de Menus
     */
    private void SalirAGrupos() {
        try {
            switch (NivelActual) {
                case ITEM_GRUPO://Volver a SubGrupo
                case GRUPO://Volver a grupo
                    cargarDatosGruposItems();
                    break;
                case SUBGRUPO:
                    cargarDatosGruposItems();
                    break;
                case ITEM_SUBGRUPO://Volver a SubGrupo
                    cargarDatosItemMenu(ItemMenuSelected.grupo, ItemMenuSelected.codigo);
                    break;
            }

        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString(), ToastManager.ERROR, 1);
        }
    }

//region Metodo AgregarItem (parametros opcionales)

    /**
     * Actualizar AdaptadorItemsPedido
     */
    private boolean AgregarItemsPedido() {
        return AgregarItemsPedido(1.0, "","");
    }

    /**
     * Agregar Items a lista Pedido
     *
     * @param cantidadItemMenu
     * @return
     */
    private boolean AgregarItemsPedido(Double cantidadItemMenu, String Extra, String CodigoExtra) {

        try {
            final Date FechaActual = new Date();
            //Agregar item a data list y luego asignar adaptador
            lstItemsPedido.add(
                    new ItemPedido(ItemMenuSelected.codigo,
                            ItemMenuSelected.descr,
                            cantidadItemMenu,
                            ItemMenuSelected.precio,
                            ItemMenuSelected.tiva,
                            ItemMenuSelected.imagen,
                            ItemMenuSelected.kp,
                            IDItem,
                            FechaActual,
                            10.0, //TODO: Cargar tasa de Servicio de Configuracion,
                            "003", //TODO: Cargar codigo empleado
                            Extra, //Extra
                            CodigoExtra, //CodigoExtra
                            0, //TODO: Cargar tipo de enviado(2 Enviado),
                            ItemMenuSelected.tipo,
                            "000", //TODO: Cargar  de correlativo de pedidos kp
                            FechaActualToString("hh:mm a"))
            );
            lvItemsPedido.setAdapter(new ItemsPedidoAdapter(this, lstItemsPedido));
            if (RellenarListVPedido(true)) {
                IDItem++;
            }
            return true;
        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
            return false;
        }
    }
//endregion

//endregion

//region Cargar Datos JSON

//region Cargar Items Menu
    /**
     * Cargar datos de BD
     *
     * @param sBuscar
     */
    private void cargarDatosItemMenu(String... sBuscar) {
        //Mostrar lista Items
        // listv.setAdapter(adapter);
        // listv.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) this);
        if (clsUtil_Net.checkWifiState(context)) {
            new AsyncLoadItemsMenu().execute(sBuscar);
        }
    }

    /**
     * Obtener los datos de la BD en un Task aparte Items segun grupo
     */
    protected class AsyncLoadItemsMenu extends AsyncTask<String, JSONObject, ArrayList<ItemMenu_Tabla>>
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
                    AsyncLoadItemsMenu.this.cancel(true);
                }
            });
        }

        @Override
        protected ArrayList<ItemMenu_Tabla> doInBackground(String... params) {
            // hacer validacion si devuelve Error o se ejecuta
            // cancel(esWSActivo = clsUtil_Net.esWSActivo(context, RestAPI.urlString));
            ArrayList<ItemMenu_Tabla> ItemsMenu = null;
            RestAPI api = new RestAPI(context);
            try {
                publishProgress();
                JSONObject jsonObj = null;
                // Si nivel =1 es un subgrupo

                if (NivelActual == enuNivelMenu.SUBGRUPO) {
                    jsonObj = api.getItemMenuporCodNivel(params[1], params[0]);
                } else {
                    jsonObj = api.getItemMenuPorGrupo(params[0]);
                }
                JSONParser parser = new JSONParser();
                ItemsMenu = parser.parseItemmenu_array(jsonObj);
                if (ItemsMenu == null) {
                    sAccion = "Valores devueltos por ItemsMenu son nulos! :/";
                    publishProgress();
                    Thread.sleep((long) (1000));
                }

                return ItemsMenu;

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
        protected void onCancelled() {
            dialogo.dismiss();
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            dialogo.setMessage(sAccion);
        }

        @Override
        protected void onPostExecute(ArrayList<ItemMenu_Tabla> ItemsMenu) {
            try {
                if (ItemsMenu != null) {
                    lstItemsMenu.clear();
                    //Cambiar Dialogo
                    for (int i = 0; i < ItemsMenu.size(); i++) {
                        lstItemsMenu.add(new ItemMenu(
                                        ItemsMenu.get(i).descr,
                                        ItemsMenu.get(i).precio,
                                        ItemsMenu.get(i).grupo,
                                        ItemsMenu.get(i).precio2,
                                        ItemsMenu.get(i).imagen,
                                        ItemsMenu.get(i).kp,
                                        ItemsMenu.get(i).nivel,
                                        ItemsMenu.get(i).codnivel,
                                        ItemsMenu.get(i).pidecanti,
                                        ItemsMenu.get(i).pidepre,
                                        ItemsMenu.get(i).tiva,
                                        ItemsMenu.get(i).codigo,
                                        ItemsMenu.get(i).retorno,
                                        ItemsMenu.get(i).descrkp,
                                        ItemsMenu.get(i).precio1,
                                        ItemsMenu.get(i).nombre,
                                        ItemsMenu.get(i).tipo,
                                        false,
                                        0)
                        );

                    }
                    gridvItemsMenu.setAdapter(null);
                    gridvItemsMenu.setAdapter(new ItemsMenuAdapter(context, lstItemsMenu));
                    if (lstItemsMenu.size() > 0) {
                        ItemMenu item = (ItemMenu) lstItemsMenu.get(0);
                        if (item.nivel == 0) {
                            NivelActual = enuNivelMenu.ITEM_GRUPO;
                        } else if (item.nivel == 2) {
                            NivelActual = enuNivelMenu.ITEM_SUBGRUPO;
                        } else if (item.nivel == 1) {
                            NivelActual = enuNivelMenu.SUBGRUPO;
                        }
                    }
                    btnIMenuGrupos_Pedido.setVisibility(View.VISIBLE);
                } else {
                    //Ocurrio Error
                    ToastManager.show(context, "No se localizaron datos. Revise la conexión al servidor :-/", ToastManager.WARNING, 0);
                    cancel(true);
                }
            } catch (Exception ex) {
                ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
                cancel(true);
            }
            dialogo.dismiss();
        }
    }
//endregion

// region Cargar Grupos
    /**
     * cargar datos de grupos Items
     */
    private void cargarDatosGruposItems() {
        //Mostrar lista Items
        if (clsUtil_Net.checkWifiState(context)) {
            new AsyncLoadGrupoItems().execute();
        }
    }

    /**
     * Obtener los datos de la BD en un Task aparte Items segun grupo
     */
    protected class AsyncLoadGrupoItems extends AsyncTask<Void, JSONObject, ArrayList<grupos_tabla>>

    {
        private String sAccion = "Cargando datos...";
        private ProgressDialog dialogo;
        ArrayList<grupos_tabla> GrupoItems = null;

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
                    AsyncLoadGrupoItems.this.cancel(true);
                }
            });
        }

        @Override
        protected ArrayList<grupos_tabla> doInBackground(Void... params) {
            // hacer validacion si devuelve Error o se ejecuta
            // cancel(esWSActivo = clsUtil_Net.esWSActivo(context, RestAPI.urlString));
            RestAPI api = new RestAPI(context);
            try {
                publishProgress();
                JSONObject jsonObj = api.getGruposMenu();
                JSONParser parser = new JSONParser();
                GrupoItems = parser.parseGrupos_array(jsonObj);
                if (GrupoItems == null) {
                    sAccion = "Valores devueltos por GrupoItems son nulos! :/";
                    publishProgress();
                    Thread.sleep((long) (1000));
                }
                return GrupoItems;

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
        protected void onCancelled() {
            dialogo.dismiss();
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            dialogo.setMessage(sAccion);
        }

        @Override
        protected void onPostExecute(ArrayList<grupos_tabla> GrupoItems) {
            try {
                if (GrupoItems != null) {
                    lstItemsMenu.clear();
                    for (int i = 0; i < GrupoItems.size(); i++) {
                        lstItemsMenu.add(new ItemMenu(
                                        null,
                                        0.0,
                                        null,
                                        0.0,
                                        GrupoItems.get(i).imagen,
                                        null,
                                        0,
                                        null,
                                        0,
                                        0,
                                        0,
                                        GrupoItems.get(i).codigo,
                                        0,
                                        null,
                                        0.0,
                                        GrupoItems.get(i).nombre,
                                        GrupoItems.get(i).tipo,
                                        GrupoItems.get(i).oculto,
                                        GrupoItems.get(i).inactivo)
                        );
                    }
                    gridvItemsMenu.setAdapter(new ItemsMenuAdapter(context, lstItemsMenu));
                    NivelActual = enuNivelMenu.GRUPO;
                    btnIMenuGrupos_Pedido.setVisibility(View.INVISIBLE);
                } else {
                    //Ocurrio Error
                    ToastManager.show(context, "No se localizaron datos. Revise la conexión al servidor :-/", ToastManager.WARNING, 0);
                    cancel(true);
                }
            } catch (Exception ex) {
                ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
                cancel(true);
            }
            dialogo.dismiss();

        }
    }
//endregion

//region Cargar ItemsPedido Procesados

//endregion



    /**
     * Maneja eventos de botones
     */
    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Bundle datosActuales = new Bundle();
                switch (v.getId()) {
                    case R.id.btnCliente_Pedido:
                        //llamar dialogo cliente
                        FragmentManager fm1 = getSupportFragmentManager();
                        dlgCliente _dlgCliente = new dlgCliente();
                        _dlgCliente.ClienteSelected = ClienteSelected;
                        _dlgCliente.show(fm1, "dlgCliente");
                        break;
                    case R.id.btnIMenuBuscar_Pedido:
                        //Llamar dialogo Buscar producto
                        FragmentManager fm2 = getSupportFragmentManager();
                        dlgItemMenuBuscar _dlgItemMenuBuscar = new dlgItemMenuBuscar();
                        _dlgItemMenuBuscar.show(fm2, "dlgItemMenuBuscar");

                        break;
                    case R.id.btnIMenuGrupos_Pedido:
                        //Mostrar Grupo de Items
                        SalirAGrupos();
                        break;
                    case R.id.btnMenu_Pedido:
                        //Llamar dialogo menu pedido
                        datosActuales.putString("NombreMesa", NombreMesa);
                        FragmentManager fm6 = getSupportFragmentManager();
                        dlgPedidoOpciones _dlgPedidoOpciones = new dlgPedidoOpciones();
                        _dlgPedidoOpciones.setArguments(datosActuales);
                        _dlgPedidoOpciones.show(fm6, "dlgPedidoOpciones");
                        switch (_enuSeleccion_OpcPedido) {
                            case IMPRIMIR_COMANDA:
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
                    case R.id.btnItemCantidad_Pedido:
                        //Llamar dialogo item cantidad
                        if (itemPedidoSelected != null) {
                            final int cantidadItem_Pedido = (int) itemPedidoSelected.cantidad;
                            dlgPedidoCantidad _dlgPedidoCantidad = new dlgPedidoCantidad();
                            datosActuales.putInt("cantidadItem_Pedido", cantidadItem_Pedido);
                            NombreItem_Pedido = itemPedidoSelected.descr;
                            datosActuales.putString("NombreItem_Pedido", NombreItem_Pedido);
                            FragmentManager fm4 = getSupportFragmentManager();
                            //_dlgPedidoCantidad.setStyle(R.style.CustomDialogTheme,);
                            _dlgPedidoCantidad.setArguments(datosActuales);
                            _dlgPedidoCantidad.show(fm4, "cantidadItem");
                        }

                        break;
                    case R.id.btnItemEliminar_Pedido:
                        //llamar dialogo item eliminar
                        if (itemPedidoSelected != null) {
                            final int cantidadItem_Pedido = (int) itemPedidoSelected.cantidad;
                            datosActuales.putInt("cantidadItem_Pedido", cantidadItem_Pedido);
                            NombreItem_Pedido = itemPedidoSelected.descr;
                            datosActuales.putString("NombreItem_Pedido", NombreItem_Pedido);
                            FragmentManager fm8 = getSupportFragmentManager();
                            dlgPedidoDevolucion _dlgPedidoDevolucion = new dlgPedidoDevolucion();
                            _dlgPedidoDevolucion.setArguments(datosActuales);
                            _dlgPedidoDevolucion.show(fm8, "dlgPedidoDevolucion");
                        }

                        break;
                    case R.id.btnItemExtra_Pedido:
                        //llamar dialogo item extra
                        final int cantidadItem_Pedido = (int) itemPedidoSelected.cantidad;
                        datosActuales.putInt("cantidadItem_Pedido", cantidadItem_Pedido);
                        NombreItem_Pedido = itemPedidoSelected.descr;
                        datosActuales.putString("NombreItem_Pedido", NombreItem_Pedido);
                        FragmentManager fm11 = getSupportFragmentManager();
                        dlgPedidoExtra _dlgPedidoExtra = new dlgPedidoExtra();
                        _dlgPedidoExtra.setReordenarItemspedido(lstItemsMenu);
                        _dlgPedidoExtra.setArguments(datosActuales);
                        _dlgPedidoExtra.show(fm11, "dlgPedidoExtra");

                        break;

                    case R.id.btnAtras_Pedido:
                        // Revisar y mandar comandas pendientes

                        //Realizar albores de Retroceder
                        ToastManager.show(pedido.this, getString(R.string.kssSaliendoPedido), ToastManager.INFORMATION, 0);

                        //cerrar Activity

                        break;
                    case R.id.imgbtnHelp_Pedido:
                        //Llamar dialogo Ayuda
                        FragmentManager fm10 = getSupportFragmentManager();
                        dlgPedidoHelp _dlgPedidoHelp = new dlgPedidoHelp();
                        _dlgPedidoHelp.show(fm10, "dlgPedidoHelp");
                        break;
                    case R.id.btnDownlstpedido_pedido:
                        int ultimoVisiable = lvItemsPedido.getLastVisiblePosition();
                        if (ultimoVisiable != lvItemsPedido.getCount()) {
                            //Desplazar hasta abajo
                            lvItemsPedido.smoothScrollToPosition(ultimoVisiable + 1);
                        }
                        break;
                    case R.id.btnUplstpedido_pedido:
                        int primeroVisible = lvItemsPedido.getFirstVisiblePosition();
                        if (primeroVisible != lvItemsPedido.getCount()) {
                            //Desplazar hacia Arriba
                            lvItemsPedido.smoothScrollToPosition(primeroVisible - 1);
                        }
                        break;
                }
            } catch (Exception ex) {
                ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
            }
        }
    };

    //guardar configuraci&#xfffd;n aplicaci&#xfffd;n Android usando SharedPreferences
    public void guardarPreferencias(View v) {
       /* try {

            SharedPreferences prefs = getSharedPreferences("preferenciasMiApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("preferenciasGuardadas", true);
            editor.putString("IVA1", txtIVA1.getText().toString());
            editor.putString("IVA2", txtIVA2.getText().toString());
            editor.putString("IVA3", txtIVA3.getText().toString());
            editor.putString("dirServerRestAPI", txtDirServer.getText().toString());
            editor.commit();
            ToastManager.show(this, "guardando preferencias", ToastManager.INFORMATION, 0);
        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString(), ToastManager.ERROR, 0);
        }*/
    }

    //cargar configuraci&#xfffd;n aplicaci&#xfffd;n Android usando SharedPreferences
    public void cargarPreferencias() {
        /*try {
            SharedPreferences prefs = getSharedPreferences("preferenciasMiApp", Context.MODE_PRIVATE);
            this.dirServerRestAPI = prefs.getString("dirServerRestAPI", "http://192.168.0.104/WebAPI_JSON_Retail/handler1.ashx");
            this.IVA1 =Double.parseDouble(prefs.getString("IVA1", "12.0"));
            this.IVA2 = Double.parseDouble(prefs.getString("IVA2", "12.0"));
            this.IVA3 = Double.parseDouble(prefs.getString("IVA3", "12.0"));
            preferenciasGuardadas = prefs.getBoolean("preferenciasGuardadas", false);
        } catch (Exception ex) {
            ToastManager.show(context, ex.getMessage().toString(), ToastManager.ERROR, 0);
        }*/
    }


}


//Enviar parametros
    /*
    protected void returnParams() {
        Intent intent = new Intent();
        intent.putExtra("result", "'Valor devuelto por ParametrosActivity'");
        setResult(OK_RESULT_CODE, intent);
        finish();
    }*/
//Con Intent
    /*
    Intent intent = new Intent();
    intent.putExtra("Numero", 2);
    //Con bundles:
    Bundle paquete = new Bundle();
    paquete.putInt("clave", 1);
    */

        /*lvItemsPedido.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ToastManager.show(context, "Tiene Foco", ToastManager.INFORMATION, 0);
            }
        });
        lvItemsPedido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ToastManager.show(context, "Algo Seleccionado", ToastManager.INFORMATION, 0);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ToastManager.show(context, "Nada Seleccionado", ToastManager.INFORMATION, 0);
            }
        });*/