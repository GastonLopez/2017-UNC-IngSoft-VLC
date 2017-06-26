package fcefyn.fcefynapp.Controladores;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import fcefyn.fcefynapp.ComunicacionClienteServidor.EnvioRequestRegistrar;

import fcefyn.fcefynapp.Interfaces.Paquete;

import fcefyn.fcefynapp.Model.PaqueteRegistro;
import fcefyn.fcefynapp.R;
import fcefyn.fcefynapp.ValidacionYEncriptado.Hash;
import fcefyn.fcefynapp.ValidacionYEncriptado.ValidatorUtil;

/**
 * A login screen that offers login via email/password.
 */
public class register extends Activity {
    private Button   btn_Reg;
    private EditText input_Email;
    private EditText input_Password;
    private EditText input_UserName;
    private EditText input_Contraseña_Repetida;
    private EditText input_telefono;
    private String url;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        input_Email = (EditText) findViewById(R.id.email);
        input_Password = (EditText) findViewById(R.id.password);
        input_telefono=(EditText) findViewById(R.id.tel);
        input_UserName = (EditText) findViewById(R.id.UserName);
        input_Contraseña_Repetida = (EditText) findViewById(R.id.rep_contraseña);

        btn_Reg = (Button) findViewById(R.id.btn_reg);
        this.url="http://181.31.120.54:5000/publicaciones/";

        btn_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encriptado;
                String mail;
                String contraseña;
                String contraseñaRepetida;
                String userName;
                boolean flagError=false;
                mail= input_Email.getText().toString();
                contraseña=input_Password.getText().toString();
                contraseñaRepetida=input_Contraseña_Repetida.getText().toString();
                userName=input_UserName.getText().toString();

                if(!ValidatorUtil.validateEmail(mail)){
                    flagError=true;
                }

                if(userName.length()<3 || userName.length()>25 ){

                    flagError=true;
                }

                if(contraseña.length()<4 || contraseña.length()>40){
                    flagError=true;
                }

                if(!contraseña.equals(contraseñaRepetida)){
                    flagError=true;
                }


                if(flagError){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Error.", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else{

                    encriptado= Hash.md5(contraseña);
                    Paquete paquete=new PaqueteRegistro(mail,encriptado, userName);
                    EnvioRequestRegistrar.getUniqueInstance().ModificarDatos(url,paquete,"PUT");

                }

            }
        });





    }


}