package com.example.kupiknigaalpha2;

public class Kniga {

	private int produkt_id;
	private String url;
	private String slika_url;
	private String naslov;
	private String cena;
	private String avtor;
	private String kategorija;
	private String godina;

	public Kniga(String url, String slika_url, String naslov,
			String cena, String avtor, String kategorija, String godina) {
		super();
		
		this.url = url;
		this.slika_url = slika_url;
		this.naslov = naslov;
		this.cena = cena;
		this.avtor = avtor;
		this.kategorija = kategorija;
		this.godina = godina;
	}

	public int getProdukt_id() {
		return produkt_id;
	}

	public void setProdukt_id(int produkt_id) {
		this.produkt_id = produkt_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSlika_url() {
		return slika_url;
	}

	public void setSlika_url(String slika_url) {
		this.slika_url = slika_url;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public String getAvtor() {
		return avtor;
	}

	public void setAvtor(String avtor) {
		this.avtor = avtor;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getGodina() {
		return godina;
	}

	public void setGodina(String godina) {
		this.godina = godina;
	}
	
	

}
