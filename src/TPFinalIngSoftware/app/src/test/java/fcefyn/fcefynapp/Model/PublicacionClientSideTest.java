package fcefyn.fcefynapp.Model;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PublicacionClientSideTest {

    PublicacionClientSide p;

    @Before
    public void setUp() throws Exception {

        p=new PublicacionClientSide("Titulo de la noticia.", "6","Deportes","Soy el cuerpo.");
    }

    @Test
    public void testGetTitulo() throws Exception {


        assertEquals("Titulo de la noticia.",p.getTitulo());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(p.getId(),"6");
    }

    @Test
    public void testGetDescripcion() throws Exception {
        assertEquals(p.getDescripcion(),"Soy el cuerpo.");
    }

    @Test
    public void testGetClasificacion() throws Exception {
        assertEquals(p.getClasificacion(),"Deportes");
    }


}