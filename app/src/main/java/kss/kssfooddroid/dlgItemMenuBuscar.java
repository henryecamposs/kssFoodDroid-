package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONObject;
import org.kobjects.util.Strings;

import java.util.ArrayList;
import java.util.List;

import Tablas.ItemMenu_Tabla;
import Tablas.JSONParser;
import Tablas.RestAPI;
import kss.kssfooddroid.util.ToastManager;
import kss.kssfooddroid.util.clsUtil_Net;

import static kss.kssfooddroid.util.clsUtil_Calculos.calcularPorcentaje;
import static kss.kssfooddroid.util.clsUtil_Calculos.calcularValorFinalConPorcentaje;

/**
 * Created by HENRY on 16/02/2015.
 */
public class dlgItemMenuBuscar extends android.support.v4.app.DialogFragment {
    ArrayList<String> dataDropDown = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private static List lstItemsMenu = new ArrayList();
    private ItemMenu ItemMenuSelected;
    private static Window window;

    private AutoCompleteTextView txtBuscarItem_IMenuBuscar;
    private static Context context;
    private TextView lblProducto_IMenuBuscar;
    private TextView lblPrecio__IMenuBuscar;
    private TextView lblIVA__IMenuBuscar;
    private TextView lblTotal__IMenuBuscar;
    private static ImageButton imgbtnbuscar;

    public void dlgItemMenuBuscar() {

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
            //TODO:Eliminar
            params.setTitle("Buscar Productos");
            window.setAttributes(params);
            // window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_itemmenu_buscar, null);
        context = getActivity();

        txtBuscarItem_IMenuBuscar = (AutoCompleteTextView) view.findViewById(R.id.txtBuscarItem_IMenuBuscar);
        lblProducto_IMenuBuscar = (TextView) view.findViewById(R.id.lblProducto_IMenuBuscar);
        lblPrecio__IMenuBuscar = (TextView) view.findViewById(R.id.lblPrecio_IMenuBuscar);
        lblIVA__IMenuBuscar = (TextView) view.findViewById(R.id.lblIVA_IMenuBuscar);
        lblTotal__IMenuBuscar = (TextView) view.findViewById(R.id.lblTotal_IMenuBuscar);

        // ocultar Edicion de clientes
        final TableLayout tblIMenuEnc_IMenuBuscar = (TableLayout) view.findViewById(R.id.tblIMenuEnc_IMenuBuscar); // Seleccionar y mostrar el layout a mostrar
        tblIMenuEnc_IMenuBuscar.setVisibility(View.GONE); //Invisible y si apartar espacio

        //Botone
        imgbtnbuscar = (ImageButton) view.findViewById(R.id.imgbtnBuscarItem_IMenuBuscar);
        imgbtnbuscar.setOnClickListener(OnClickListener);
        final ImageButton Okbtn = (ImageButton) view.findViewById(R.id.imgbtnOK_IMenuBuscar);
        Okbtn.setOnClickListener(OnClickListener);
        final ImageButton Cancelbtn = (ImageButton) view.findViewById(R.id.imgbtnCancel_IMenuBuscar);
        Cancelbtn.setOnClickListener(OnClickListener);

        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_circ_itembuscar);
        alertDialogBuilder.setTitle(R.string.kssBuscarItemMenu);
        txtBuscarItem_IMenuBuscar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtBuscarItem_IMenuBuscar.selectAll();
                ItemMenuSelected = (ItemMenu) lstItemsMenu.get(position);
                if (ItemMenuSelected != null) {
                    //Llenar lbl
                    lblProducto_IMenuBuscar.setText(ItemMenuSelected.descr);
                    lblPrecio__IMenuBuscar.setText(ItemMenuSelected.precio.toString());
                    Double IVA_tasa = 0.0;

                    if (ItemMenuSelected.tiva > 0) {
                        switch (ItemMenuSelected.tiva) {
                            case 1:
                                //TODO: Cargar IVA de Configuracion
                                IVA_tasa = 12.0;
                                break;
                            case 2:
                                IVA_tasa = 8.0;
                                break;
                            case 3:
                                IVA_tasa = 22.0;
                                break;
                        }
                        lblIVA__IMenuBuscar.setText(String.format("%,10.2f",calcularPorcentaje(IVA_tasa, ItemMenuSelected.precio)));
                        lblTotal__IMenuBuscar.setText(String.format("%,10.2f", calcularValorFinalConPorcentaje(IVA_tasa, ItemMenuSelected.precio)));
                    } else {
                        lblIVA__IMenuBuscar.setText("**EXCENTO**");
                        lblTotal__IMenuBuscar.setText(String.format("%,10.2f", ItemMenuSelected.precio));
                    }
                    ToastManager.show(context, "Ha seleccionado: \n" + ItemMenuSelected.descr.toString(), ToastManager.INFORMATION, 0);
                    tblIMenuEnc_IMenuBuscar.setVisibility(View.VISIBLE);
                }
            }
        });

        txtBuscarItem_IMenuBuscar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txtBuscarItem_IMenuBuscar.selectAll();
                txtBuscarItem_IMenuBuscar.showDropDown();
                return false;
            }
        });

        return alertDialogBuilder.create();
    }

    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgbtnBuscarItem_IMenuBuscar:
                    String sBuscar = txtBuscarItem_IMenuBuscar.getText().toString().trim();
                    if (sBuscar.length() > 0) {
                        cargarDatosItemMenu(sBuscar);
                    } else {
                        ToastManager.show(context, "Debe indicar el texto a buscar :-/", ToastManager.INFORMATION, 1);
                    }
                    break;
                case R.id.imgbtnOK_IMenuBuscar:
                    ((pedido) getActivity()).setItemEncontrado(ItemMenuSelected);
                    dismiss();
                    break;
                case R.id.imgbtnCancel_IMenuBuscar:
                    //Realizar albores de Cancel
                    ToastManager.show(getActivity(), getString(R.string.kssNohaSeleccionadoItem), ToastManager.WARNING, 0);
                    dismiss();
                    break;
            }
        }
    };


    /**
     * Cargar datos de BD
     *
     * @param sBuscar
     */
    private void cargarDatosItemMenu(String... sBuscar) {
        dataDropDown = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(context,
                android.R.layout.select_dialog_item, dataDropDown);
        txtBuscarItem_IMenuBuscar.setAdapter(adapter);
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

                jsonObj = api.getItemMenuPorDescr(params[0]);
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
                if (ItemsMenu != null && ItemsMenu.size() != 0) {
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
                        String descr = ItemsMenu.get(i).descr.trim();
                        dataDropDown.add(String.format("%s Bs. %,10.2f",
                                Strings.fill(descr, 70 - descr.length(), '.'),
                                ItemsMenu.get(i).precio));
                    }
                    adapter.notifyDataSetChanged();
                    Thread.sleep(200);
                    txtBuscarItem_IMenuBuscar.showDropDown();
                    txtBuscarItem_IMenuBuscar.selectAll();
                } else {
                    //Ocurrio Error
                    if (ItemsMenu.size() == 0) {
                        ToastManager.show(context, "No se localizaron datos :-/", ToastManager.WARNING, 0);
                    } else {
                        ToastManager.show(context, "No se localizaron datos. Revise la conexión al servidor :-/", ToastManager.WARNING, 0);
                    }
                    cancel(true);
                }
            } catch (Exception ex) {
                ToastManager.show(context, ex.getMessage().toString() + "\n" + ex.getLocalizedMessage().toString(), ToastManager.ERROR, 1);
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
