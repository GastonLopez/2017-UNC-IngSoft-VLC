package fcefyn.fcefynapp.ComunicacionClienteServidor;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fcefyn.fcefynapp.Vistas.CrearModifBorrarPublicaciones;
import fcefyn.fcefynapp.Interfaces.EnviarPaquetes;
import fcefyn.fcefynapp.Interfaces.Paquete;
import fcefyn.fcefynapp.Model.PaqueteRegistro;
import fcefyn.fcefynapp.Controladores.login;

public class EnvioRequestRegistrar extends Activity implements EnviarPaquetes {
    private static EnvioRequestRegistrar uniqueInstance = new EnvioRequestRegistrar();


    private EnvioRequestRegistrar() {

    }




    public static EnvioRequestRegistrar getUniqueInstance(){
        synchronized (EnvioDeRequest.class){
            if(uniqueInstance==null){
                uniqueInstance=new EnvioRequestRegistrar();
            }
        }
        return uniqueInstance;
    }

    @Override
    public void ModificarDatos(String url, Paquete publicacion, String metodo) {
        PaqueteRegistro publicacion1=(PaqueteRegistro) publicacion;
        RequestQueue queue = Volley.newRequestQueue(this);



        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("acc", "nueva_acc");
        params.put("pwd", publicacion1.getPassword());
        params.put("nombre",publicacion1.getNombre());
        params.put("email",publicacion1.getEmail());



        JSONObject jsonObj = new JSONObject(params);
        JsonObjectRequest jsonObjRequest;
        // Request a json response from the provided URL
        if(metodo.equals("POST")) {
            jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EnvioRequestRegistrar.this, CrearModifBorrarPublicaciones.class);
                    startActivity(intent);
                    login.setPermiso(true);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
            // Add the request to the RequestQueue.
            queue.add(jsonObjRequest);
            Intent intent = new Intent(EnvioRequestRegistrar.this, login.class);
            startActivity(intent);

        }

    }
}
