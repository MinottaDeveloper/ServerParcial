package com.example.jerryminotta.preparacionparcialclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receptor extends Thread {
	
	Socket s;

	//paso 2
	OnMessage observer;
	
	public Receptor(Socket s) {
		this.s=s;
		
	}

	@Override
	public void run() {
		try {
			InputStream is = s.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			while(true) {
				String line = reader.readLine();
				Log.e("debut","Conexion exitosa");

				//paso 4
				observer.onReceived(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//observable
	//paso 1
	public interface  OnMessage{
		public void onReceived(String mensaje);
	}

	//paso 3
	public void setObserver( OnMessage observer){
		this.observer=observer;

	}

	
	
}
