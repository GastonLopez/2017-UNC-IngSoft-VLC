package fcefyn.fcefynapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import fcefyn.fcefynapp.ComunicacionClienteServidor.ReciboDeRequest;
import fcefyn.fcefynapp.Controladores.ControllerGetListas;
import fcefyn.fcefynapp.Controladores.login;
import fcefyn.fcefynapp.Interfaces.Contexto;
import fcefyn.fcefynapp.Interfaces.ControllerMenuInterface;
import fcefyn.fcefynapp.Model.PublicacionClientSide;
import fcefyn.fcefynapp.R;

public class listadoPublicaciones extends AppCompatActivity implements Contexto {
    private static final String url="http://181.31.120.54:5000/publicaciones/";

    private ControllerMenuInterface controller;
    private TextView ed1;
    private TextView ed2;
    private TextView ed3;
    private TextView ed4;
    private TextView ed5;
    private TextView ed6;
    private TextView ed7;
    private TextView ed8;
    private TextView ed9;
    private TextView ed10;


    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;

    ArrayList<TextView> arraylist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_publicaciones);
        setStrategy(new ControllerGetListas());
        ed1 = (TextView) findViewById(R.id.ed1);
        ed2 = (TextView) findViewById(R.id.ed2);
        ed3 = (TextView) findViewById(R.id.ed3);
        ed4 = (TextView) findViewById(R.id.ed4);
        ed5 = (TextView) findViewById(R.id.ed5);
        ed6 = (TextView) findViewById(R.id.ed6);
        ed7 = (TextView) findViewById(R.id.ed7);
        ed8 = (TextView) findViewById(R.id.ed8);
        ed9 = (TextView) findViewById(R.id.ed9);
        ed10 = (TextView) findViewById(R.id.ed10);
        arraylist = new ArrayList();
        arraylist.add(ed1);
        arraylist.add(ed2);
        arraylist.add(ed3);
        arraylist.add(ed4);
        arraylist.add(ed5);
        arraylist.add(ed6);
        arraylist.add(ed7);
        arraylist.add(ed8);
        arraylist.add(ed9);
        arraylist.add(ed10);
        peticionesHTTPGet(arraylist);
        ed1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VisualizarPublicacion.class);
                startActivity(intent);
                //String titulo="Noticia1.";
                //String contenido="Acá va el cuerpo de la noticia.";
                //VisualizarPublicacion.actualizar(titulo, contenido);
                //clickHaciaVisualizarPublicacion(arraylist, 0);
            }
        });
        /**ed2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 1);
            }
        });
        ed3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 2);
            }
        });
        ed4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 3);
            }
        });
        ed5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 4);
            }
        });
        ed6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 5);
            }
        });
        ed7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 6);
            }
        });
        ed8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 7);
            }
        });
        ed9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 8);
            }
        });
        ed10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.clickHaciaVisualizarPublicacion(arraylist, 9);
            }
        });


        b1 = (Button) findViewById(R.id.borrar_1);
        b2 = (Button) findViewById(R.id.borrar_2);
        b3 = (Button) findViewById(R.id.borrar_3);
        b4 = (Button) findViewById(R.id.borrar_4);
        b5 = (Button) findViewById(R.id.borrar_5);
        b6 = (Button) findViewById(R.id.borrar_6);
        b7 = (Button) findViewById(R.id.borrar_7);
        b8 = (Button) findViewById(R.id.borrar_8);
        b9 = (Button) findViewById(R.id.borrar_9);
        b10 = (Button) findViewById(R.id.borrar_10);

        if (login.getPermiso()) {
            b1.setEnabled(true);
            b1.setVisibility(View.VISIBLE);
            b2.setEnabled(true);
            b2.setVisibility(View.VISIBLE);
            b3.setEnabled(true);
            b3.setVisibility(View.VISIBLE);
            b4.setEnabled(true);
            b4.setVisibility(View.VISIBLE);
            b5.setEnabled(true);
            b5.setVisibility(View.VISIBLE);
            b6.setEnabled(true);
            b6.setVisibility(View.VISIBLE);
            b7.setEnabled(true);
            b7.setVisibility(View.VISIBLE);
            b8.setEnabled(true);
            b8.setVisibility(View.VISIBLE);
            b9.setEnabled(true);
            b9.setVisibility(View.VISIBLE);
            b10.setEnabled(true);
            b10.setVisibility(View.VISIBLE);

            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(1);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(2);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(3);
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(4);
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(5);
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(6);
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(7);
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(8);
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(9);
                }
            });
            b10.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    controller.BorrarOModificarScreen(10);
                }
            });**/


        }


    //}

    /*public void clickHaciaVisualizarPublicacion(ArrayList<TextView> arrayList, int indice){
        JSONArray jsonArray=ReciboDeRequest.getUniqueInstance().makeWebServiceCall("");
        CharSequence titulo="";
        CharSequence contenido="";
        try {
            //JSONObject jObj = jsonArray.getJSONObject(indice);
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
    }**/




    public void peticionesHTTPGet(ArrayList<TextView> arrayList){
        //JSONArray jsonArray= ReciboDeRequest.getUniqueInstance().makeWebServiceCall(url);
        String [] s = ReciboDeRequest.getUniqueInstance().hacerWebServiceCall(url);
        for(int i=0; i<1;i++){
            try{
                //JSONObject jObj=jsonArray.getJSONObject(i);
                String titulo=s[0];
                String id=s[1];
                String clasif=s[2];
                String descripcion=s[3];
                PublicacionClientSide publicacion=new PublicacionClientSide(titulo,id,clasif,descripcion);
                //CharSequence titulo=publicacion.getTitulo();
                //CharSequence contenido=publicacion.getDescripcion();
                //arrayList.get(i).setText(titulo);
                ed1.setText(publicacion.getTitulo());
            }
            catch (Exception e){
                Toast toast1 = Toast.makeText(getApplicationContext(), "No hay más publicaciones.", Toast.LENGTH_SHORT);
                toast1.show();
            }


        }
    }
    @Override
    public void setStrategy(ControllerMenuInterface c) {
        this.controller=c;
    }

}
