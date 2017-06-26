package fcefyn.fcefynapp.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fcefyn.fcefynapp.Controladores.ControllerCrearModificarPublicaciones;
import fcefyn.fcefynapp.Interfaces.Contexto;
import fcefyn.fcefynapp.Interfaces.ControllerMenuInterface;
import fcefyn.fcefynapp.R;
import fcefyn.fcefynapp.Controladores.login;

public class CrearModifBorrarPublicaciones extends AppCompatActivity implements Contexto {
    private ControllerMenuInterface controller;
    private Button btn_crear;
    private Button btn_modificar;

    private Button btn_close_sesion;
    boolean permiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_modif_borrar_publicaciones);
        setStrategy(new ControllerCrearModificarPublicaciones());
        permiso=true;


        btn_crear=(Button) findViewById(R.id.btn_crear);
        btn_modificar=(Button) findViewById(R.id.btn_modif);
        btn_close_sesion=(Button) findViewById(R.id.btn_cerrarSesion);



        btn_close_sesion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controller.clickCerrarSesion(); //Envío de eventos a controller.
            }
        });


        btn_crear.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                if(login.getPermiso()){
                    controller.crearPublicacion();

                }

            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                if(login.getPermiso()){
                    controller.editarPublicacion();
                }

            }
        });


    }

    @Override
    public void setStrategy(ControllerMenuInterface c) {
        this.controller=c;
    }
}
