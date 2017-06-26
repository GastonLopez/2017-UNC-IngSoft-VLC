package fcefyn.fcefynapp.Controladores;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fcefyn.fcefynapp.R;

public class ConsultasYContactos extends AppCompatActivity {
    Button btn_llamar;
    Button btn_enviarMail;

    EditText edSubj;
    EditText edBody;

    Button btn_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_ycontactos);

        btn_llamar = (Button) findViewById(R.id.buttonLlamar);
        btn_enviarMail = (Button) findViewById(R.id.botonMail);
        edSubj = (EditText) findViewById(R.id.subject);
        edBody = (EditText) findViewById(R.id.cuerpo);
        btn_atras = (Button) findViewById(R.id.atras);


        btn_llamar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+3571590901")); //
                startActivity(i);
            }
        });
        btn_enviarMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String email = "FCEFyNAPP@gmail.com";
                String subject = edSubj.getText().toString();
                String body = edBody.getText().toString();

                 String uriText = "mailto:" + Uri.encode(email) +
                        "?subject=" + Uri.encode(subject) +
                        "&body=" + Uri.encode(body);
                 Uri uri = Uri.parse(uriText);
                intent.setData(uri);
                //Iniciamos la actividad
                startActivity(intent);

            }
        });

        btn_atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendIntent = new Intent(ConsultasYContactos.this, Menu.class);
                startActivity(sendIntent);
            }
        });


    }


}
