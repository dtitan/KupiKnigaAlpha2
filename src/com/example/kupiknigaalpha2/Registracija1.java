package com.example.kupiknigaalpha2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registracija1 extends BaseKupiKniga {

	EditText ime;
	EditText prezime;
	EditText email;
	EditText pass;
	EditText vipkod;
	TextView os;
	CheckBox checkbox;
	Button next;

	Intent i;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.registracija1);

		ime = (EditText) findViewById(R.id.et_reg_ime);
		prezime = (EditText) findViewById(R.id.et_reg_prezime);
		email = (EditText) findViewById(R.id.et_reg_email);
		pass = (EditText) findViewById(R.id.et_reg_password);
		vipkod = (EditText) findViewById(R.id.et_reg_vipkod);
		checkbox = (CheckBox) findViewById(R.id.cb_reg_checkbox);
		next = (Button) findViewById(R.id.btn_reg_sledno);
		os = (TextView) findViewById(R.id.tv_reg_opuslovi);

		// onClickListener za kopceto sledno
		next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (!checkbox.isChecked()) {
					Toast.makeText(getApplicationContext(),
							"Мора да се согласите со условите", 0).show();
				} else if (ime.length() == 0 || prezime.length() == 0
						|| email.length() == 0 || pass.length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Полињата со ѕвездичка се задолжителни", 0).show();
				} else {
					i = new Intent(Registracija1.this, Registracija2.class);
					i.putExtra("mail", email.getText().toString());
					startActivity(i);
				}

			}
		});

		os.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(android.content.Intent.ACTION_VIEW);
				i.setData(Uri
						.parse("http://kupikniga.mk/Generalprovisions.aspx"));
				startActivity(i);

			}
		});

	}

}
