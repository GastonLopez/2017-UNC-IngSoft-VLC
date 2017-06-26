package fcefyn.fcefynapp.Interfaces;


import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public interface ControllerMenuInterface {
    public void clickHaciaPublicaciones();
    public void clickHaciaConsultas();
    public void clickHaciaLogin();
    public void clickHaciaOrigenPublicaciones();
    public void clickHaciaRegistrar();
    public void llamar();
    public void enviarMail( EditText editTextSubject, EditText editTextBody);
    public void atras();
    public void Registrar(EditText nombreDeUsuario, EditText email, EditText telefono, EditText Password,EditText Contraseña_Repetida);
    public void Loguear(EditText Email,EditText Password);
    public void peticionesHTTPGet(ArrayList<TextView> arraylist);
    public void clickHaciaVisualizarPublicacion(ArrayList<TextView> arrayList, int indice);
    public void clickCerrarSesion();
    public void crearPublicacion();
    public void editarPublicacion();
    public void modificarPublicacion();
    public void borrarPublicacion();
    public void modificarPublicacion(int indice);
    public void borrarPublicacion(int indice);
    public Paquete getPublicacionHttp(int n);

    public void crearPublicacion(String titulo,String id,String clasificador,String descripcion);
    public void BorrarOModificarScreen(int indice);
}
