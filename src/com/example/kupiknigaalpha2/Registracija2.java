package com.example.kupiknigaalpha2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Registracija2 extends BaseKupiKniga {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.registracija2);
		
		Intent i = getIntent();
		
		TextView v = (TextView)findViewById(R.id.et_reg2_mail);
		v.setText(i.getExtras().getString("mail"));
		
		
		Button b = (Button) findViewById(R.id.btn_reg2_home);

		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(Registracija2.this, MainActivity.class));

			}
		});
	}

}
