package kss.kssfooddroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import kss.kssfooddroid.util.ToastManager;

/**
 * Created by HENRY on 17/02/2015.
 */
public class dlgUsuarioPass extends DialogFragment {
    private static String pass="";
    public void dlgUsuarioPass(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_usuario_pass, null);
        //Botone
        final ImageButton Okbtn= (ImageButton) view.findViewById(R.id.imgbtnOK_UserPass);
        Okbtn.setOnClickListener(OnClickListener);
        final ImageButton Cancelbtn= (ImageButton) view.findViewById(R.id.imgbtnCancel_UserPass);
        Cancelbtn.setOnClickListener(OnClickListener);


        final TextView txtpass=(TextView) view.findViewById(R.id.txtPass_UserPass);
        txtpass.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                if(txtpass.getText().toString().length()>0){
                    pass=txtpass.getText().toString();
                }

            }
        });

        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setIcon(R.drawable.btn_usuariopass);
        alertDialogBuilder.setTitle(R.string.kssPasswordUsuario);

        return alertDialogBuilder.create();
    }
    private View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            switch(v.getId()){
                case R.id.imgbtnOK_UserPass:
                    // Realizar labores de OK
                    if (pass.length()>0){
                        ((prueba_DlgVarios) getActivity()).UsuerioPass(pass);
                        dismiss();
                    }else {
                        ToastManager.show(getActivity(), "Debe indicar una contraseña para continuar.",ToastManager.INFORMATION,0);
                    }
                    break;
                case R.id.imgbtnCancel_UserPass:
                    //Realizar albores de Cancel
                    ToastManager.show(getActivity(), "No ha Seleccionado un Item de Menú", ToastManager.WARNING, 0);
                    dismiss();
                    break;
            }
        }
    };
}
