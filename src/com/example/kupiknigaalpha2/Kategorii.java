package com.example.kupiknigaalpha2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Kategorii extends BaseKupiKniga {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.kategorii);

		ListView lv = (ListView) findViewById(R.id.lv_kategorii);
		String[] kategorii = { "Атласи и Енциклопедии", "Белетристика",
				"Бизнис", "Честитки", "Детски Книги", "Лектири", "Езотериј",
				"Живот", "Здравје", "Карти и Мапи", "Класична Литература",
				"Книги за 18+", "Книги за млади", "Комплети", "Монографии",
				"Мултимедија", "Останато", "Поезија", "Популарна Психологија",
				"Речници и Лексикони", "Стрипови", "Стручна Литература",
				"Тероија на Литература", "Туризам", "Филозофија и Религија",
				"ДВД", "Сувенири", "Накит" };

		// First paramenter - Context
		// Second parameter - Layout for the row								// Gi ima samoto android
		// Third parameter - ID of the TextView to which the data is written	// Gi ima samoto android
		// Forth - the Array of data
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,kategorii);

		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getBaseContext(), "Test Click"+arg2, Toast.LENGTH_SHORT).show();
				
			}
		});

	}

}
