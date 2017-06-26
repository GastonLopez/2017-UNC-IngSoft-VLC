package fcefyn.fcefynapp.ComunicacionClienteServidor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;


public class ReciboDeRequestTest {

    @Test
    public void testMakeWebServiceCall() throws Exception {
        ReciboDeRequest.getUniqueInstance().makeWebServiceCall("http://181.31.120.54:5000/publicaciones/");

}}