package kss.kssconsumirws_rest.kss_dbf;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kss.kssconsumirws_rest.LoginActivity;
import kss.kssconsumirws_rest.R;


public class ItemMenu_buscar extends ActionBarActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu_buscar);
        final EditText txt= (EditText) findViewById(R.id.txtBuscar_Prod);
        final Button btn= (Button) findViewById(R.id.btnbuscar_prod);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    //cargar Activity
                        Intent i=new Intent(ItemMenu_buscar.this,ItemMenuDetalle.class);
                        i.putExtra("descr",txt.getText().toString());
                        startActivity(i);
                                    }
                                }

        );


    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_menu_buscar, menu);
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
    }*/
}
