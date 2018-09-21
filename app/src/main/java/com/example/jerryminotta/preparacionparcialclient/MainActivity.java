package com.example.jerryminotta.preparacionparcialclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Receptor.OnMessage {


    Button btn_enviar;
    EditText et_coorX, et_coorY, et_radio;
    TextView tv_advertencia;
    Cliente c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = new Cliente(this , null);
        c.start();

        et_coorX = findViewById(R.id.et_coorX);
        et_coorY = findViewById(R.id.et_coorY);
        et_radio = findViewById(R.id.et_radio);

        btn_enviar=findViewById(R.id.btn_enviar);

        tv_advertencia= findViewById(R.id.tv_advertencia);


        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(et_coorX.getText().length()!=0 &&  et_coorY.getText().length()!=0  && et_radio.getText().length()!=0){

                    String x= et_coorX.getText().toString();
                    String y= et_coorY.getText().toString();
                    String radio= et_radio.getText().toString();



                    c.mensaje= x+','+y+','+radio;

                    c.enviarDatos();

                    et_coorX.setText("");
                    et_coorY.setText("");
                    et_radio.setText("");
                    tv_advertencia.setText("");

                } else {
                    tv_advertencia.setText("COMPLETE TODOS LOS CAMPOS!");
                }
            }
        });



    }


    @Override
    public void onReceived(final String mensaje) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_advertencia.setText(mensaje);
            }
        });
    }
}
