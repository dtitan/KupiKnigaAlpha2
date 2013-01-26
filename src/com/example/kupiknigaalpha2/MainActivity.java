package com.example.kupiknigaalpha2;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.util.ByteArrayBuffer;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends BaseKupiKniga {

	String NAMESPACE = "http://tempuri.org/";
	String METHOD_NAME = "HelloWorld";
	String SOAP_ACTION = "http://tempuri.org/HelloWorld";
	String URL = "http://192.168.0.100/service/service.asmx";

	KnigaNaDenot knd = new KnigaNaDenot();
	String text;
	ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();//preporacani knigi
	HashMap<String, String> knigand;//kniga na denot

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Da nema naslov na activity gore
		//setTitle("");
		//Da moze da se pristapuva na net od glavniot tred
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// /////////////////////////////////////////////////////////////////////
		SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

		SoapSerializationEnvelope soapEnvelope;

		soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(Request);

		HttpTransportSE htp = new HttpTransportSE(URL);

		try {
			htp.call(SOAP_ACTION, soapEnvelope);
			SoapPrimitive resultString = (SoapPrimitive) soapEnvelope
					.getResponse();
			text = resultString.toString();
			Log.i("Ima text", text);

		} catch (Exception e) {
			Log.i("Nema tekst", "nema");
		}
		// ////////////////////////////////////////////////////////////////////////
		Document doc = XMLfromString(text);

		NodeList nodes = doc.getElementsByTagName("Produkt");
		//Polnenje na objekti od XML-ot
		for (int i = 0; i < nodes.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();

			Element e = (Element) nodes.item(i);
			map.put("url", XMLfunctions.getValue(e, "Link"));

			map.put("slika_url", XMLfunctions.getValue(e, "Slika"));
			map.put("naslov", XMLfunctions.getValue(e, "Ime"));
			map.put("cena", XMLfunctions.getValue(e, "Cena"));
			map.put("avtor", XMLfunctions.getValue(e, "Avtor"));
			map.put("kategorija", XMLfunctions.getValue(e, "Kategorija"));
			map.put("godina", XMLfunctions.getValue(e, "Godina"));
			map.put("rejting", XMLfunctions.getValue(e, "Rejting"));
			map.put("opis", XMLfunctions.getValue(e, "Opis"));
			myList.add(map);
			
		}
		// ///////////////////////////////////////////////////////////////////
		ImageView im1 = (ImageView) findViewById(R.id.slika1);
		ImageView im2 = (ImageView) findViewById(R.id.slika2);
		ImageView im3 = (ImageView) findViewById(R.id.slika3);
		ImageView im4 = (ImageView) findViewById(R.id.slika4);
		ImageView im5 = (ImageView) findViewById(R.id.slika5);
		ImageView im6 = (ImageView) findViewById(R.id.slika6);
		ImageView im7 = (ImageView) findViewById(R.id.slika7);
		ImageView im8 = (ImageView) findViewById(R.id.slika8);
		ImageView im9 = (ImageView) findViewById(R.id.slika9);
		ImageView im10 = (ImageView) findViewById(R.id.slika10);

		// Ima prazni mesta vo linkovie za slikite i mora da se zamenat so %20
		for (int i = 0; i < myList.size(); i++) {
			String nov = myList.get(i).get("slika_url");
			nov = nov.replace(" ", "%20");
			myList.get(i).put("slika_url", nov);

		}
		//downoladiranje na i postavuvanje na slikite		
		ImageDownloader s = new ImageDownloader();
		s.download(myList.get(0).get("slika_url"), im1);
		s.download(myList.get(1).get("slika_url"), im2);
		s.download(myList.get(2).get("slika_url"), im3);
		s.download(myList.get(3).get("slika_url"), im4);
		s.download(myList.get(4).get("slika_url"), im5);
		s.download(myList.get(5).get("slika_url"), im6);
		s.download(myList.get(6).get("slika_url"), im7);
		s.download(myList.get(7).get("slika_url"), im8);
		s.download(myList.get(8).get("slika_url"), im9);
		s.download(myList.get(9).get("slika_url"), im10);
		
		
		//КНИГА НА ДЕНОТ
		knigand = knd.getKnigaNaDenotXML();
		
		ImageView iv_knd = (ImageView)findViewById(R.id.iv_main_knd);
		TextView tv_knd = (TextView)findViewById(R.id.tv_main_kndopis);
		TextView tv_knd_naslov = (TextView)findViewById(R.id.tv_main_naslovknd);
		
		
		
		s.download(knigand.get("slika_url"), iv_knd);
		String kratokopis = knigand.get("opis").substring(0, 200);
		tv_knd.setText(kratokopis+"...");
		//tv_knd_naslov.setText("Некој Нов Подолг Наслов Од Предходниот Уштее подолг од другиот");
		tv_knd_naslov.setText(knigand.get("naslov"));
		//End Kniga Na Denot
		
		
		//da se zemi slikata od ImageView
		Bitmap m = ((BitmapDrawable)im1.getDrawable()).getBitmap();
		
		
	}//END onCreate

	public Bitmap getBitmap(String bitmapUrl) {
		try {
			URL url = new URL(bitmapUrl);
			BitmapFactory.Options opt = new BitmapFactory.Options();

			// open connection
			URLConnection ucon = url.openConnection();
			// buffering...
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is, 1024);
			// get the bytes
			int current = 0;
			ByteArrayBuffer baf = new ByteArrayBuffer(1024);

			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			ByteArrayInputStream imageStream = new ByteArrayInputStream(
					baf.toByteArray());

			return BitmapFactory.decodeStream(imageStream);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public void categoriesClick(View v){
		startActivity(new Intent(this, Kategorii.class));
	}

	//Klikanje na sllikte od preporacanite knigi
	@SuppressLint("ShowToast")
	public void slikaClick(View v) {
		Intent i = new Intent(MainActivity.this, EdnaKnigaActivity.class);

		switch (v.getId()) {
		case R.id.slika1:
			i.putExtra("naslov", myList.get(0).get("naslov"));
			i.putExtra("avtor", myList.get(0).get("avtor"));
			i.putExtra("cena", myList.get(0).get("cena"));
			i.putExtra("slika_url", myList.get(0).get("slika_url"));
			i.putExtra("rejting", myList.get(0).get("rejting"));
			i.putExtra("opis", myList.get(0).get("opis"));

			break;
		case R.id.slika2:
			i.putExtra("naslov", myList.get(1).get("naslov"));
			i.putExtra("avtor", myList.get(1).get("avtor"));
			i.putExtra("cena", myList.get(1).get("cena"));
			i.putExtra("slika_url", myList.get(1).get("slika_url"));
			i.putExtra("rejting", myList.get(1).get("rejting"));
			i.putExtra("opis", myList.get(1).get("opis"));

			break;
		case R.id.slika3:
			i.putExtra("naslov", myList.get(2).get("naslov"));
			i.putExtra("avtor", myList.get(2).get("avtor"));
			i.putExtra("cena", myList.get(2).get("cena"));
			i.putExtra("slika_url", myList.get(2).get("slika_url"));
			i.putExtra("rejting", myList.get(2).get("rejting"));
			i.putExtra("opis", myList.get(2).get("opis"));
			break;
		case R.id.slika4:
			i.putExtra("naslov", myList.get(3).get("naslov"));
			i.putExtra("avtor", myList.get(3).get("avtor"));
			i.putExtra("cena", myList.get(3).get("cena"));
			i.putExtra("slika_url", myList.get(3).get("slika_url"));
			i.putExtra("rejting", myList.get(3).get("rejting"));
			i.putExtra("opis", myList.get(3).get("opis"));
			break;
		case R.id.slika5:
			i.putExtra("naslov", myList.get(4).get("naslov"));
			i.putExtra("avtor", myList.get(4).get("avtor"));
			i.putExtra("cena", myList.get(4).get("cena"));
			i.putExtra("slika_url", myList.get(4).get("slika_url"));
			i.putExtra("rejting", myList.get(4).get("rejting"));
			i.putExtra("opis", myList.get(4).get("opis"));
			break;
		case R.id.slika6:
			i.putExtra("naslov", myList.get(5).get("naslov"));
			i.putExtra("avtor", myList.get(5).get("avtor"));
			i.putExtra("cena", myList.get(5).get("cena"));
			i.putExtra("slika_url", myList.get(5).get("slika_url"));
			i.putExtra("rejting", myList.get(5).get("rejting"));
			i.putExtra("opis", myList.get(5).get("opis"));
			break;
		case R.id.slika7:
			i.putExtra("naslov", myList.get(6).get("naslov"));
			i.putExtra("avtor", myList.get(6).get("avtor"));
			i.putExtra("cena", myList.get(6).get("cena"));
			i.putExtra("slika_url", myList.get(6).get("slika_url"));
			i.putExtra("rejting", myList.get(6).get("rejting"));
			i.putExtra("opis", myList.get(6).get("opis"));
			break;
		case R.id.slika8:
			i.putExtra("naslov", myList.get(7).get("naslov"));
			i.putExtra("avtor", myList.get(7).get("avtor"));
			i.putExtra("cena", myList.get(7).get("cena"));
			i.putExtra("slika_url", myList.get(7).get("slika_url"));
			i.putExtra("rejting", myList.get(7).get("rejting"));
			i.putExtra("opis", myList.get(7).get("opis"));
			break;
		case R.id.slika9:
			i.putExtra("naslov", myList.get(8).get("naslov"));
			i.putExtra("avtor", myList.get(8).get("avtor"));
			i.putExtra("cena", myList.get(8).get("cena"));
			i.putExtra("slika_url", myList.get(8).get("slika_url"));
			i.putExtra("rejting", myList.get(8).get("rejting"));
			i.putExtra("opis", myList.get(8).get("opis"));
			break;
		case R.id.slika10:
			i.putExtra("naslov", myList.get(9).get("naslov"));
			i.putExtra("avtor", myList.get(9).get("avtor"));
			i.putExtra("cena", myList.get(9).get("cena"));
			i.putExtra("slika_url", myList.get(9).get("slika_url"));
			i.putExtra("rejting", myList.get(9).get("rejting"));
			i.putExtra("opis", myList.get(9).get("opis"));
			break;

		default:
			break;
		}
		startActivity(i);
	}
	
	public void onClickKnigaNaDenot(View v){
		Intent i = new Intent(MainActivity.this,EdnaKnigaActivity.class);
		i.putExtra("naslov", knigand.get("naslov"));
		i.putExtra("avtor", knigand.get("avtor"));
		i.putExtra("cena", knigand.get("cena"));
		i.putExtra("slika_url", knigand.get("slika_url"));
		i.putExtra("rejting", knigand.get("rejting"));
		i.putExtra("opis", knigand.get("opis"));
		startActivity(i);
	}

	public Document XMLfromString(String v) {

		Document doc = null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(v));
			doc = db.parse(is);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Wrong XML file structure: " + e.getMessage());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;

	}

}
