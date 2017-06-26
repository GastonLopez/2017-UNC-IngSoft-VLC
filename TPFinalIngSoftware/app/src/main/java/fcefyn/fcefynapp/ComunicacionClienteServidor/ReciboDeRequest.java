package fcefyn.fcefynapp.ComunicacionClienteServidor;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class ReciboDeRequest {
    static ReciboDeRequest uniqueInstance;
    static String response = null;
    public final static int GETRequest = 1;
    //public final static int POSTRequest = 2;

    //Constructor with no parameter
    private ReciboDeRequest() {
    }

    public static ReciboDeRequest getUniqueInstance(){
        synchronized (ReciboDeRequest.class){
            if(uniqueInstance==null){
                uniqueInstance=new ReciboDeRequest();
            }
        }
        return uniqueInstance;
    }


    /**
     * Making web service call
     *
     * @url - url to make web request
     * @requestmethod - http request method
     */

    public String[] hacerWebServiceCall(String url) {
        String[] s={"Noticia1.","1","General","Cuerpo de la noticia1."};
        return s;
    }

    public JSONArray makeWebServiceCall(String url) {
        return this.makeWebServiceCall(url,  null);
    }

    /**
     * Making web service call
     *
     * @url - url to make web request
     * @requestmethod - http request method
     * @params - http request params
     */
    public   JSONArray makeWebServiceCall(String urladdress, HashMap<String, String> params) {
        URL url;
        String response = "";
        JSONArray jsonArray;
        jsonArray=new JSONArray();
        try {
            url = new URL(urladdress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15001);
            conn.setConnectTimeout(15001);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("GETRequest");

            int reqresponseCode = conn.getResponseCode();

            if (reqresponseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
            jsonArray = new JSONArray(response);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonArray;

    }
}




