package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	private String straat;
	private String huisNr;
	private String postCode;
	private String Gemeente;
	
	protected Adres(){}

	protected Adres(String straat, String huisNr, String postCode, String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postCode = postCode;
		Gemeente = gemeente;
	}
	
	public static boolean isStraatValid(String straat) {
		return straat != null && ! straat.trim().isEmpty();
	}
	
	public static boolean isHuisNrValid(String huisNr) {
		return huisNr != null && ! huisNr.trim().isEmpty();
	}
	
	public static boolean isPostCodeValid(String postCode) {
		return postCode != null && ! postCode.trim().isEmpty();
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getGemeente() {
		return Gemeente;
	}
	
	
}
