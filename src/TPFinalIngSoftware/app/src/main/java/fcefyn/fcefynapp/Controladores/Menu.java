package fcefyn.fcefynapp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fcefyn.fcefynapp.R;
import fcefyn.fcefynapp.Vistas.listadoPublicaciones;

public class Menu extends AppCompatActivity {
    private Button btn_estudiantes;
    private Button btn_login;
    private Button btn_carreras;
    private Button btn_salud;
    private Button btn_apuntes;
    private Button btn_contactos_consultas;


    private Button btn_cultura_deportes;
    private Button btn_ultimas_noticias;
    private Button btn_info_admin;
    private Button btn_reg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_estudiantes=(Button)findViewById(R.id.estudiantes);
        btn_login=(Button)findViewById(R.id.login);
        btn_carreras=(Button)findViewById(R.id.carreras);
        btn_salud=(Button)findViewById(R.id.salud);
        btn_apuntes=(Button)findViewById(R.id.apuntes);
        btn_contactos_consultas=(Button)findViewById(R.id.contactosYConsultas);

        btn_cultura_deportes=(Button)findViewById(R.id.culturaYDeportes);
        btn_ultimas_noticias=(Button)findViewById(R.id.ultimasNews);
        btn_info_admin=(Button)findViewById(R.id.infoAdmin);
        btn_reg=(Button)findViewById(R.id.reg);



        btn_reg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),register.class);
                startActivity(i);


            }
        });

        btn_estudiantes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });

        btn_cultura_deportes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }
        });
        btn_contactos_consultas.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),ConsultasYContactos.class);
                startActivity(i);

            }
        });
        btn_apuntes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }

        });

        btn_carreras.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }

        });

        btn_info_admin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }

        });

        btn_salud.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }

        });

        btn_ultimas_noticias.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),listadoPublicaciones.class);
                startActivity(i);
            }

        });

    }

}
