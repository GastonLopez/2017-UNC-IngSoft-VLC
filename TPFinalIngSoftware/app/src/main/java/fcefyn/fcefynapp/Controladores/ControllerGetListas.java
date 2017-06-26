package fcefyn.fcefynapp.Controladores;


import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import fcefyn.fcefynapp.Interfaces.ControllerMenuInterface;
import fcefyn.fcefynapp.Vistas.CrearPublicacion;
import fcefyn.fcefynapp.ComunicacionClienteServidor.EnvioDeRequest;
import fcefyn.fcefynapp.Interfaces.Paquete;
import fcefyn.fcefynapp.Model.PublicacionClientSide;
import fcefyn.fcefynapp.ComunicacionClienteServidor.ReciboDeRequest;
import fcefyn.fcefynapp.Vistas.BorrarOModificarScreen;
import fcefyn.fcefynapp.Vistas.VisualizarPublicacion;

public class ControllerGetListas extends Activity implements ControllerMenuInterface {
    String url;
    public ControllerGetListas(){
        this.url="http://181.31.120.54:5000/publicaciones/";
    }

    @Override
    public void crearPublicacion(){
        Intent intent=new Intent(ControllerGetListas.this, CrearPublicacion.class);
        startActivity(intent);
    }

    @Override
    public void modificarPublicacion(int indice) {
            BorrarOModificarScreen.setModificar(true);
            Intent i=new Intent(ControllerGetListas.this,CrearPublicacion.class);
            getIntent().putExtra("indice",indice);
            startActivity(i);

    }

    @Override
    public void borrarPublicacion(int indice) {
        try {
            JSONArray jsonA = ReciboDeRequest.getUniqueInstance().makeWebServiceCall(url);
            JSONObject jObj = jsonA.getJSONObject(indice);
            PublicacionClientSide publicacion = new PublicacionClientSide(jObj);
            EnvioDeRequest.getUniqueInstance().ModificarDatos(url,publicacion,"DELETE");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Intent i=new Intent(ControllerGetListas.this,Menu.class);
        startActivity(i);
    }

    @Override
    public void BorrarOModificarScreen(int indice) {
        Intent intent=new Intent(ControllerGetListas.this,BorrarOModificarScreen.class);
        intent.putExtra("indice", indice);
        startActivity(intent);
    }



    public void crearPublicacion(String titulo, String id, String clasificador, String descripcion) {
        PublicacionClientSide publicacion=new PublicacionClientSide(titulo,id,clasificador,descripcion);
        if(BorrarOModificarScreen.isModificar()){
            EnvioDeRequest.getUniqueInstance().ModificarDatos(this.url,  publicacion,"POST");
            BorrarOModificarScreen.setModificar(false);
        }
        else{
            EnvioDeRequest.getUniqueInstance().ModificarDatos(this.url,  publicacion,"PUT");
        }

        Intent i=new Intent(ControllerGetListas.this,Menu.class);
        startActivity(i);
    }

    public Paquete getPublicacionHttp(int n){
        PublicacionClientSide publicacion;
        try {
            JSONArray jsonA = ReciboDeRequest.getUniqueInstance().makeWebServiceCall(url);
            JSONObject jObj = jsonA.getJSONObject(n);
            publicacion = new PublicacionClientSide(jObj);
            return publicacion;

        }
        catch(Exception e){
            e.printStackTrace();
            return new Paquete() {
            };
        }


    }




    public void peticionesHTTPGet(ArrayList<TextView> arrayList){
        JSONArray jsonArray=ReciboDeRequest.getUniqueInstance().makeWebServiceCall(url);

        for(int i=0; i<arrayList.size();i++){
            try{
                JSONObject jObj=jsonArray.getJSONObject(i);
                PublicacionClientSide publicacion=new PublicacionClientSide(jObj);
                CharSequence titulo=publicacion.getTitulo();
                //CharSequence contenido=publicacion.getDescripcion();
                arrayList.get(i).setText(titulo);
            }
            catch (Exception e){
                Toast toast1 = Toast.makeText(getApplicationContext(), "No hay más publicaciones.", Toast.LENGTH_SHORT);
                toast1.show();
            }


        }
    }

    public void clickHaciaVisualizarPublicacion(ArrayList<TextView> arrayList, int indice){
        JSONArray jsonArray=ReciboDeRequest.getUniqueInstance().makeWebServiceCall("");
        CharSequence titulo="";
        CharSequence contenido="";
        try {
            JSONObject jObj = jsonArray.getJSONObject(indice);
            PublicacionClientSide publicacion = new PublicacionClientSide(jObj);
            titulo = publicacion.getTitulo();
            contenido = publicacion.getDescripcion();

        }
        catch (Exception e){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Error al acceder a la publicación.", Toast.LENGTH_SHORT);
                toast1.show();
        }


        Intent intent=new Intent(ControllerGetListas.this,VisualizarPublicacion.class);
        startActivity(intent);
        VisualizarPublicacion.actualizar(titulo, contenido);
    }






    @Override
    public void enviarMail(EditText editTextSubject, EditText editTextBody) {

    }

    @Override
    public void clickHaciaRegistrar() {

    }

    @Override
    public void Loguear(EditText Email, EditText Password) {

    }

    @Override
    public void clickHaciaOrigenPublicaciones() {

    }


    @Override
    public void clickHaciaPublicaciones() {

    }

    @Override
    public void clickHaciaConsultas() {

    }

    @Override
    public void borrarPublicacion() {

    }

    @Override
    public void modificarPublicacion() {

    }

    @Override
    public void clickCerrarSesion() {

    }

    @Override
    public void editarPublicacion() {

    }

    @Override
    public void clickHaciaLogin() {

    }

    @Override
    public void atras() {

    }


    @Override
    public void llamar() {

    }

    @Override
    public void Registrar(EditText nombreDeUsuario, EditText email, EditText telefono, EditText Password, EditText Contraseña_Repetida) {

    }
}
