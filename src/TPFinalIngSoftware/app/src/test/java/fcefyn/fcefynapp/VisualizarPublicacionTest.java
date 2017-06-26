package fcefyn.fcefynapp;

import android.app.Activity;
import android.content.Intent;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;


import fcefyn.fcefynapp.Vistas.VisualizarPublicacion;

import static org.junit.Assert.*;


public class VisualizarPublicacionTest   {



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void probarSetearValorTitulo() {

        String contenido;
        String titulo;
        titulo="Soy titulo.";
        contenido="Soy contenido.";


        assertNull(VisualizarPublicacion.getTitulo());

        VisualizarPublicacion.setTitulo(titulo);
        assertNotNull(VisualizarPublicacion.getTitulo());

        VisualizarPublicacion.setContenido(contenido);
        assertNotNull(VisualizarPublicacion.getContenido());



        assertEquals(titulo, VisualizarPublicacion.getTitulo());
        assertEquals(contenido,VisualizarPublicacion.getContenido());

    }



    @After
    public void tearDown() throws Exception {

    }
}