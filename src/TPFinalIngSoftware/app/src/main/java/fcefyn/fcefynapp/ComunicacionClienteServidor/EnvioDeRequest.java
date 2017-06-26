package fcefyn.fcefynapp.ComunicacionClienteServidor;

import android.app.Activity;
import android.os.Bundle;
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

import fcefyn.fcefynapp.Interfaces.EnviarPaquetes;
import fcefyn.fcefynapp.Interfaces.Paquete;
import fcefyn.fcefynapp.Model.PublicacionClientSide;


public class EnvioDeRequest extends Activity implements EnviarPaquetes {
    private static EnvioDeRequest uniqueInstance = new EnvioDeRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private EnvioDeRequest() {

    }




    public static EnvioDeRequest getUniqueInstance(){
        synchronized (EnvioDeRequest.class){
            if(uniqueInstance==null){
                uniqueInstance=new EnvioDeRequest();
            }
        }
        return uniqueInstance;
    }


    @Override

    public void ModificarDatos(String url, Paquete publicacion, String metodo) {
        PublicacionClientSide publicacion1=(PublicacionClientSide) publicacion;
        RequestQueue queue = Volley.newRequestQueue(this);



        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", publicacion1.getTitulo());
        params.put("id", publicacion1.getId());
        params.put("content", publicacion1.getDescripcion());
        params.put("clasificacion", publicacion1.getClasificacion());


        JSONObject jsonObj = new JSONObject(params);
        JsonObjectRequest  jsonObjRequest;
        // Request a json response from the provided URL
        if(metodo.equals("POST")) {
            jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
        else if(metodo.equals("PUT")){
            jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
        else if(metodo.equals("DELETE")){
            jsonObjRequest = new JsonObjectRequest(Request.Method.DELETE, url, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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




