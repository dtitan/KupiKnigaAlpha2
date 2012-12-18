package com.example.kupiknigaalpha2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

	}

}
