package fcefyn.fcefynapp.Controladores;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fcefyn.fcefynapp.ComunicacionClienteServidor.EnvioRequestLogin;


import fcefyn.fcefynapp.Interfaces.Paquete;
import fcefyn.fcefynapp.Model.PaqueteLogin;
import fcefyn.fcefynapp.R;
import fcefyn.fcefynapp.ValidacionYEncriptado.Hash;
import fcefyn.fcefynapp.ValidacionYEncriptado.ValidatorUtil;


public class login extends Activity  {
    private Button   btn_Login;
    private EditText input_Email;
    private EditText input_Password;

    private static boolean permiso;
    String url;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input_Email = (EditText) findViewById(R.id.inputEmail);
        input_Password = (EditText) findViewById(R.id.password);
        btn_Login = (Button) findViewById(R.id.signInBtn);
        this.url="http://181.31.120.54:5000/publicaciones/";
        permiso=false;
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encriptado;
                String contraseña;
                String mail;
                boolean flagError=false;
                mail= input_Email.getText().toString();
                contraseña=input_Password.getText().toString();

                if(!ValidatorUtil.validateEmail(mail)){
                    flagError=true;
                }

                if(contraseña.length()<4 || contraseña.length()>40){
                    flagError=true;
                }
                if(flagError){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Error.", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else{

                    encriptado= Hash.md5(contraseña);
                    Paquete paquete=new PaqueteLogin(mail,encriptado);
                    EnvioRequestLogin.getUniqueInstance().ModificarDatos(url,paquete,"POST");

                }

            }
        });


}



    public static boolean getPermiso(){
        return permiso;
    }

    public static void setPermiso(boolean v){
        permiso=v;
    }
}
