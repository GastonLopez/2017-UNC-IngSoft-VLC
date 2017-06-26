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
import fcefyn.fcefynapp.Model.PaqueteLogin;
import fcefyn.fcefynapp.Controladores.login;

public class EnvioRequestLogin extends Activity implements EnviarPaquetes {

        private static EnvioRequestLogin uniqueInstance = new EnvioRequestLogin();


        private EnvioRequestLogin() {

        }




        public static EnvioRequestLogin getUniqueInstance(){
            synchronized (EnvioDeRequest.class){
                if(uniqueInstance==null){
                    uniqueInstance=new EnvioRequestLogin();
                }
            }
            return uniqueInstance;
        }

    @Override
    public void ModificarDatos(String url, Paquete publicacion, String metodo) {
        PaqueteLogin publicacion1=(PaqueteLogin) publicacion;
        RequestQueue queue = Volley.newRequestQueue(this);



        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("acc", publicacion1.getEmail());
        params.put("pwd", publicacion1.getPassword());



        JSONObject jsonObj = new JSONObject(params);
        JsonObjectRequest jsonObjRequest;
        // Request a json response from the provided URL
        if(metodo.equals("POST")) {
            jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EnvioRequestLogin.this, CrearModifBorrarPublicaciones.class);
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

        }

    }
}
