package com.example.kupiknigaalpha2;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class EdnaKnigaActivity extends BaseKupiKniga{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edna_kniga);
		setTitle("");		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//////////////////////////////////////////////////////
		Intent i = getIntent();
		ImageDownloader imgdwn = new ImageDownloader();
		
		ImageView v = (ImageView)findViewById(R.id.iv_ednakniga_slika);
		imgdwn.download(i.getExtras().getString("slika_url"), v);
		
		TextView naslov = (TextView)findViewById(R.id.tv_ednakniga_naslov);
		naslov.setText(""+i.getExtras().getCharSequence("naslov"));
		
		TextView avtor = (TextView)findViewById(R.id.tv_ednakniga_avtor);
		avtor.setText("Автор : "+i.getExtras().getCharSequence("avtor"));
		
		TextView cena = (TextView)findViewById(R.id.tv_ednakniga_cena);
		cena.setText("Цена : "+i.getExtras().getCharSequence("cena"));
		
		RatingBar rb = (RatingBar)findViewById(R.id.ratingBar1);
		rb.setRating(Float.parseFloat(i.getExtras().getString("rejting")));
		
		if(i.getExtras().getString("opis")!=null){
			TextView opis = (TextView)findViewById(R.id.tv_ednakniga_opis);
			opis.setText(i.getExtras().getString("opis"));
		}
		
	}

}
