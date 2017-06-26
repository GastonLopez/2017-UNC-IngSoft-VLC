package fcefyn.fcefynapp.Vistas;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fcefyn.fcefynapp.Controladores.ControllerGetListas;
import fcefyn.fcefynapp.Interfaces.Contexto;
import fcefyn.fcefynapp.Interfaces.ControllerMenuInterface;
import fcefyn.fcefynapp.Model.PublicacionClientSide;
import fcefyn.fcefynapp.R;

public class CrearPublicacion extends AppCompatActivity implements Contexto {
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText ed4;
    private Button btn_crear;
    private ControllerMenuInterface controller;
    private int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        ed4=(EditText)findViewById(R.id.ed4);
        btn_crear=(Button)findViewById(R.id.btn_crear);
        setStrategy(new ControllerGetListas());
        if(BorrarOModificarScreen.isModificar()){
             btn_crear.setText("Modificar");
             PublicacionClientSide p=(PublicacionClientSide) controller.getPublicacionHttp(numero);

             ed1.setText(p.getTitulo());
             ed2.setText(p.getId());
             ed3.setText(p.getClasificacion());
             ed4.setText(p.getDescripcion());
             btn_crear.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.crearPublicacion(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(),ed4.getText().toString() ); //Envío de eventos a controller.
                 }
            });




        }
        else {
            btn_crear.setText("Crear");
            btn_crear.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.crearPublicacion(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), ed4.getText().toString()); //Envío de eventos a controller.
                }
            });
        }






    }

    @Override
    public void setStrategy(ControllerMenuInterface c) {
        this.controller=c;
    }
}
