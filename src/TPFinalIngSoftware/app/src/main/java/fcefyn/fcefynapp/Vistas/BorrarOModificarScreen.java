package fcefyn.fcefynapp.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fcefyn.fcefynapp.Interfaces.Contexto;
import fcefyn.fcefynapp.Controladores.ControllerGetListas;
import fcefyn.fcefynapp.Interfaces.ControllerMenuInterface;
import fcefyn.fcefynapp.R;

public class BorrarOModificarScreen extends AppCompatActivity implements Contexto {
    private Button btn_b;
    private Button btn_m;
    private Button btn_c;
    private int i;
    private ControllerMenuInterface controller;
    private static boolean  Modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_omodificar_screen);
        btn_b=(Button)findViewById(R.id.btn_borrar);
        btn_m=(Button)findViewById(R.id.btn_modificar);
        btn_c=(Button)findViewById(R.id.btn_crear);
        i=(int)getIntent().getExtras().getSerializable("indice");
        setStrategy(new ControllerGetListas());
        Modificar=false;
        btn_b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controller.borrarPublicacion(i);
            }

        });

        btn_m.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controller.modificarPublicacion(i);
            }

        });

        btn_c.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controller.crearPublicacion();
            }

        });






    }

    public static boolean isModificar() {
        return Modificar;
    }

    public static void setModificar(boolean modificar) {
        Modificar = modificar;
    }

    @Override
    public void setStrategy(ControllerMenuInterface c) {
        this.controller=c;
    }
}
