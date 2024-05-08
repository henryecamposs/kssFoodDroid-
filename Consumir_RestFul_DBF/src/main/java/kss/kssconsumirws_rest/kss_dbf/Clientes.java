package kss.kssconsumirws_rest.kss_dbf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kss.kssconsumirws_rest.R;


public class Clientes extends Activity {
    TextView lblnombre;
    EditText txtNomnbre;
    TextView lblResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        lblnombre= (TextView) findViewById(R.id.lblResult);
        txtNomnbre=(EditText) findViewById(R.id.txtNombreCli);
        lblResult= (TextView) findViewById(R.id.lblResult);
        Button btn= (Button) findViewById(R.id.btnbuscarCliente);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(Clientes.this,ClientesDetalle.class);
                        i.putExtra("nombre",txtNomnbre.getText().toString());
                        startActivity(i);
                    }
                });

    }


}
