package com.example.kupiknigaalpha2;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

public class KnigaNaDenot {
	
	String NAMESPACE = "http://tempuri.org/";
	String METHOD_NAME = "EdnaKniga";
	String SOAP_ACTION = "http://tempuri.org/EdnaKniga";
	String URL = "http://192.168.0.101/service/service.asmx";
	
	HashMap<String, String> kniga = new HashMap<String, String>();
	
	String responseXmlString;
	XMLfunctions xmlf = new XMLfunctions();
	
	public HashMap<String, String> getKnigaNaDenotXML(){
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		
		SoapSerializationEnvelope envelope;
		
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE htp = new HttpTransportSE(URL);
		
		try {
			htp.call(SOAP_ACTION, envelope);
			SoapPrimitive string = (SoapPrimitive)envelope.getResponse();
			
			responseXmlString = string.toString();
			
		} catch (Exception e) {
			
		}
		
		Document doc = XMLfromString(responseXmlString);
		NodeList nodes = doc.getElementsByTagName("Produkt");
		
		Element e = (Element)nodes.item(0);
		
		kniga.put("slika_url", xmlf.getValue(e, "Slika"));
		kniga.put("naslov", xmlf.getValue(e, "Ime"));
		kniga.put("cena", xmlf.getValue(e, "Cena"));
		kniga.put("avtor", xmlf.getValue(e, "Avtor"));
		kniga.put("kategorija", xmlf.getValue(e, "Kategorija"));
		kniga.put("godina", xmlf.getValue(e, "Godina"));
		kniga.put("rejting", xmlf.getValue(e, "Rejting"));
		kniga.put("opis", xmlf.getValue(e, "Opis"));
		
		
		return kniga;
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
