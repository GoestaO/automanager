package automanager;

import java.io.Serializable;

public class Auto implements Serializable {
	private String hersteller;
	private int leistung;
	private int anzahlTueren;
	private boolean hatAnhaengerkupplung;

	// Getter
	public String getHersteller() {
		return hersteller;
	}

	public int getLeistung() {
		return leistung;
	}

	public int getAnzahlTueren() {
		return anzahlTueren;
	}

	public boolean getHatAnhaengerkupplung() {
		return hatAnhaengerkupplung;
	}

	// Setter
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public void setLeistung(int leistung) {
		this.leistung = leistung;
	}

	public void setAnzahlTueren(int anzahlTueren) {
		this.anzahlTueren = anzahlTueren;
	}

	public void setHatAnhaengerkupplung(boolean hatAnhaengerkupplung) {
		this.hatAnhaengerkupplung = hatAnhaengerkupplung;
	}

	// Tuning-Methode
	public void tunen(int tuneWert) {
		this.leistung = this.leistung + tuneWert;
	}

	public Auto(String hersteller, int leistung, int anzahlTueren) {
		this.hersteller = hersteller;
		this.leistung = leistung;
		this.anzahlTueren = anzahlTueren;
	}
	
	public Auto(String hersteller, int leistung, int anzahlTueren,
			boolean hatAnhaengerkupplung) {
		this(hersteller, leistung, anzahlTueren);
		this.hatAnhaengerkupplung = hatAnhaengerkupplung;
	}

}
