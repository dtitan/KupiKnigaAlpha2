package com.example.kupiknigaalpha2;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseKupiKniga {

	String NAMESPACE = "http://tempuri.org/";
	String METHOD_NAME = "Korisnik";
	String SOAP_ACTION = "http://tempuri.org/Korisnik";
	String URL = "http://192.168.1.101/service/service.asmx";
	// //////////////////////////////////////////////////////////////

	EditText username;
	EditText passwd;
	Button login;
	Button register;

	String responseText;
	SoapObject request;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in_register_layout);

		username = (EditText) findViewById(R.id.ET_lir_username);
		passwd = (EditText) findViewById(R.id.ET_lir_password);
		login = (Button) findViewById(R.id.BTN_lir_log_in);
		register = (Button) findViewById(R.id.BTN_lir_register);

		request = new SoapObject(NAMESPACE, METHOD_NAME);

		

		// logiranje
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				request = new SoapObject(NAMESPACE, METHOD_NAME);
				request.addProperty("username", username.getText().toString());
				request.addProperty("password", passwd.getText().toString());

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE htp = new HttpTransportSE(URL);

				try {
					htp.call(SOAP_ACTION, envelope);
					SoapPrimitive string = (SoapPrimitive) envelope.getResponse();

					responseText = string.toString();

				} catch (Exception e) {

				}
				
				Toast.makeText(getBaseContext(), responseText, Toast.LENGTH_SHORT).show();

				
			}
		});

		// registracija
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,
						Registracija1.class));

			}
		});
	}

}
