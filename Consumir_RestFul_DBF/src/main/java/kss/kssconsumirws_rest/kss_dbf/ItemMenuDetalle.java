package kss.kssconsumirws_rest.kss_dbf;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONObject;

import kss.kssconsumirws_rest.JSONParser;
import kss.kssconsumirws_rest.R;
import kss.kssconsumirws_rest.RestAPI;
import kss.kssconsumirws_rest.kss_dbf.tablas.ItemMenu_Tabla;


public class ItemMenuDetalle extends ActionBarActivity {
        TextView lblProd, lblprecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu__detalle);
        lblProd= (TextView) findViewById(R.id.lblResult_Descr );
        lblprecio= (TextView) findViewById(R.id.lblResult_precio);
        Intent i=getIntent();
        String codigo=i.getStringExtra("descr");
        new AsyncUserDetails().execute(codigo);
    }

    protected class AsyncUserDetails extends AsyncTask<String,Void,ItemMenu_Tabla>
    {

        @Override
        protected ItemMenu_Tabla doInBackground(String... params) {
            ItemMenu_Tabla userDetail=null;
            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.getItemMenuPorDescr(params[0]);
                JSONParser parser = new JSONParser();
                userDetail = parser.parserItemMenu(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncUserDetails", e.getMessage());
            }

            return userDetail;
        }

        @Override
        protected void onPostExecute(ItemMenu_Tabla result) {
            // TODO Auto-generated method stub

            lblProd.setText(result.descr);
            lblprecio.setText(result.precio.toString());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_menu__detalle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
