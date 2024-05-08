package kss.kssconsumirws_rest.kss_dbf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import kss.kssconsumirws_rest.JSONParser;
import kss.kssconsumirws_rest.R;
import kss.kssconsumirws_rest.RestAPI;
import kss.kssconsumirws_rest.kss_dbf.tablas.cliente_tabla;
import kss.kssconsumirws_rest.tablas_class.DeptTable;
import kss.kssconsumirws_rest.tablas_class.UserDetailsTable;


public class ClientesDetalle extends Activity implements AdapterView.OnItemClickListener {
TextView lblresult;
    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_detalle);
lblresult= (TextView) findViewById(R.id.lblResult);

        Intent i=getIntent();
        String username=i.getStringExtra("nombre");

        data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.lstClientes);
        context = this;

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);
        listv.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) this);
        new AsyncLoadClientes().execute(username);
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        String Item= adapter.getItemAtPosition(position).toString();
        lblresult.setText(Item);
    }

    protected class AsyncLoadClientes extends
            AsyncTask<String, JSONObject, ArrayList<cliente_tabla>>
            //AsyncTask<String,Void,cliente_tabla>
    {

        @Override
        protected ArrayList<cliente_tabla> doInBackground(String... params) {
            // TODO Auto-generated method stub
            ArrayList<cliente_tabla> cliente=null;
            RestAPI api = new RestAPI();
            try {
                JSONObject jsonObj = api.getclientesPorNombre(params[0]);
                JSONParser parser = new JSONParser();
                cliente = parser.parseCliente_array(jsonObj);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncUserDetails", e.getMessage());
            }
            return cliente;
        }



        @Override
        protected void onPostExecute(ArrayList<cliente_tabla> cliente) {
            // TODO Auto-generated method stub
            for (int i = 0; i < cliente.size(); i++) {
                data.add(cliente.get(i).rif.trim () + " " + cliente.get(i).nombre.trim());
            }
            adapter.notifyDataSetChanged();
            lblresult.setText(cliente.get(0).nombre.trim() + "-" + cliente.get(0).rif.trim());
            //tvLastName.setText(result.getLastName());
        }



    }


}
