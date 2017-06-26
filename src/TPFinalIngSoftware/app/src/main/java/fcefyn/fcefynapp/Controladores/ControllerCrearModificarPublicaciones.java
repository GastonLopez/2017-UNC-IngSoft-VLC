package fcefyn.fcefynapp.Controladores;


import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import fcefyn.fcefynapp.Interfaces.ControllerMenuInterface;
import fcefyn.fcefynapp.Vistas.CrearPublicacion;
import fcefyn.fcefynapp.Interfaces.Paquete;
import fcefyn.fcefynapp.Vistas.listadoPublicaciones;

public class ControllerCrearModificarPublicaciones extends Activity implements ControllerMenuInterface {

    public void clickCerrarSesion(){
        login.setPermiso(false);
        Intent intent=new Intent(ControllerCrearModificarPublicaciones.this,login.class);
        startActivity(intent);
    }

    @Override
    public void crearPublicacion() {
        Intent intent=new Intent(ControllerCrearModificarPublicaciones.this, CrearPublicacion.class);
        startActivity(intent);
    }

    @Override
    public void modificarPublicacion() {

    }

    @Override
    public void modificarPublicacion(int indice) {

    }

    @Override
    public void borrarPublicacion() {

    }

    @Override
    public void borrarPublicacion(int indice) {

    }

    @Override
    public Paquete getPublicacionHttp(int n) {
        return null;
    }

    @Override
    public void BorrarOModificarScreen(int indice) {

    }

    @Override
    public void crearPublicacion(String titulo, String id, String clasificador, String descripcion) {

    }

    @Override
    public void editarPublicacion() {
        Intent intent=new Intent(ControllerCrearModificarPublicaciones.this,listadoPublicaciones.class);
        startActivity(intent);
    }

    @Override
    public void Registrar(EditText nombreDeUsuario, EditText email, EditText telefono, EditText Password, EditText Contraseña_Repetida) {

    }

    @Override
    public void clickHaciaVisualizarPublicacion(ArrayList<TextView> arrayList, int indice) {

    }

    @Override
    public void peticionesHTTPGet(ArrayList<TextView> arraylist) {

    }

    @Override
    public void atras() {

    }

    @Override
    public void Loguear(EditText Email, EditText Password) {

    }

    @Override
    public void llamar() {

    }

    @Override
    public void enviarMail(EditText editTextSubject, EditText editTextBody) {

    }

    @Override
    public void clickHaciaPublicaciones() {

    }

    @Override
    public void clickHaciaRegistrar() {

    }

    @Override
    public void clickHaciaOrigenPublicaciones() {

    }

    @Override
    public void clickHaciaLogin() {

    }

    @Override
    public void clickHaciaConsultas() {

    }
}
