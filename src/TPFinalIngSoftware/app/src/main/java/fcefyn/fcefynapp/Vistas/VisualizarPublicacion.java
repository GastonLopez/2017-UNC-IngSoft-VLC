package fcefyn.fcefynapp.Vistas;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;

import fcefyn.fcefynapp.R;


public class VisualizarPublicacion extends AppCompatActivity {
    private static String titulo;
    private static String contenido;

    private static TextView tituloScreen, contenidoScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_publicacion);
        tituloScreen = (TextView) findViewById(R.id.tituloTexto);
        contenidoScreen = (TextView) findViewById(R.id.contenidoTexto);
        titulo="Noticia1";
        contenido="Cuerpo de la Noticia.";
        //titulo=getIntent().getExtras().get("titulo").toString();
        //contenido=getIntent().getExtras().get("contenido").toString();
        actualizar(titulo,contenido);

    }

    public static String getTitulo() {
        return titulo;
    }

    public static String getContenido() {
        return contenido;
    }

    public static String getTituloScreen() {
        return tituloScreen.getText().toString();
    }

    public static String getContenidoScreen() {
        return contenidoScreen.getText().toString();
    }

    public static  void setTitulo(String s){
        titulo=s;
    }

    public static void setContenido(String contenido) {
        VisualizarPublicacion.contenido = contenido;
    }

    public static void actualizar(CharSequence titulo, CharSequence contenido) {
        tituloScreen.setText(titulo);
        contenidoScreen.setText(contenido);
    }


}
