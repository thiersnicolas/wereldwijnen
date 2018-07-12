package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;

@Embeddable
public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;
	private long aantal;
	private BigDecimal prijs;
	
	/*@ManyToOne(optional = false, cascade = CascadeType.REMOVE, targetEntity= Bestelbon.class)
	@JoinColumn(name="bonid")
	private Bestelbon bestelbon;*/
	
	@ManyToOne(optional=false, targetEntity=Wijn.class)
	@JoinColumn(name="wijnid")
	private Wijn wijn;

	protected Bestelbonlijn(){}

	public Bestelbonlijn(long aantal, Bestelbon bestelbon, Wijn wijn) {
		if (isAantalValid(aantal)) {
			this.aantal = aantal;
		}
		this.prijs = wijn.getPrijs();
		//this.bestelbon = bestelbon;
		this.wijn = wijn;
		//bestelbon.addBestelbonlijn(this);
		/*wijn.addBestelbonlijn(this);*/
	}
	
	public static boolean isAantalValid(long aantal) {
		return aantal>0 && aantal%1==0;
	}

	public long getAantal() {
		return aantal;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	/*public Bestelbon getBestelbon() {
		return bestelbon;
	}*/

	public Wijn getWijn() {
		return wijn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (aantal ^ (aantal >>> 32));
		/*result = prime * result + ((bestelbon == null) ? 0 : bestelbon.hashCode());*/
		result = prime * result + ((wijn == null) ? 0 : wijn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Bestelbonlijn)) {
			return false;
		}
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (aantal != other.aantal) {
			return false;
		}
		/*if (bestelbon == null) {
			if (other.bestelbon != null) {
				return false;
			}
		} else if (!bestelbon.equals(other.bestelbon)) {
			return false;
		}*/
		if (wijn == null) {
			if (other.wijn != null) {
				return false;
			}
		} else if (!wijn.equals(other.wijn)) {
			return false;
		}
		return true;
	}
}
