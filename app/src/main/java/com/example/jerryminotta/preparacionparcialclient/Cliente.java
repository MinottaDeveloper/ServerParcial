package com.example.jerryminotta.preparacionparcialclient;

import android.app.Activity;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends Thread{

	Socket s;
	Receptor r;
	String mensaje;

	MainActivity activity;

	public Cliente(MainActivity activity, String mensaje){
		this.activity = activity;
        this.mensaje=mensaje;
	}

	@Override
	public void run() {
		try {
			System.out.println("Ejecita-----------------------------------------------------------------------------------------------------------------");
		 s = new Socket("10.0.2.2",5000);
			//s = new Socket("10.0.2.2",5000);
			r = new Receptor(s);
			r.setObserver(activity);
			r.start();


			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarDatos( ){



		new Thread(new Runnable() {
			@Override
			public void run() {

				try{
					OutputStream os = s.getOutputStream();
					PrintWriter out = new PrintWriter( new OutputStreamWriter(os) );
					out.println(mensaje);
					out.flush();
				}catch (IOException e){

				}

			}
		}).start();



	}

}
