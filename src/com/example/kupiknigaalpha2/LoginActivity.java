package com.example.kupiknigaalpha2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseKupiKniga{
	
	EditText username;
	EditText passwd;
	Button login;
	Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.log_in_register_layout);
			
			 username = (EditText)findViewById(R.id.ET_lir_username);
			 passwd = (EditText)findViewById(R.id.ET_lir_password);
			 login = (Button)findViewById(R.id.BTN_lir_log_in);
			 register = (Button)findViewById(R.id.BTN_lir_register);
			
			 //logiranje
			login.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Log.i("Username", username.getText().toString());
					Log.i("Password", passwd.getText().toString());
					
				}
			});
			
			//registracija
			register.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					startActivity(new Intent(LoginActivity.this, Registracija1.class));
					
				}
			});
	}
	
	

}
