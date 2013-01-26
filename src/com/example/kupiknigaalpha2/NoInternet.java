package com.example.kupiknigaalpha2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class NoInternet extends BaseKupiKniga {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.nointernet);
		onCreateDialog(0);
		
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		
		return new AlertDialog.Builder(this)
		.setIcon(R.drawable.logo)
		.setTitle("Нема Интернет Конекција")
		.setPositiveButton("OK", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				finish();
				
			}
		}).create();
	}
	
	
	

}
