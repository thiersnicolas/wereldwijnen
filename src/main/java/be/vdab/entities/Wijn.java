package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="wijnen")
public class Wijn implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne(optional=false)
	@JoinColumn(name="soortid")
	private Soort soort;
	private int jaar;
	private int beoordeling;
	private BigDecimal prijs;
	private long inBestelling;
	@Version
	private long versie;
	protected Wijn() {}
	
	public void setInBestelling(long aantal) {
		inBestelling = inBestelling + aantal;
	}

	public long getId() {
		return id;
	}

	public Soort getSoort() {
		return soort;
	}

	public int getJaar() {
		return jaar;
	}

	public int getBeoordeling() {
		return beoordeling;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public long getInBestelling() {
		return inBestelling;
	}

	/*public Set<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beoordeling;
		result = prime * result + jaar;
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
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
		if (!(obj instanceof Wijn)) {
			return false;
		}
		Wijn other = (Wijn) obj;
		if (beoordeling != other.beoordeling) {
			return false;
		}
		if (jaar != other.jaar) {
			return false;
		}
		if (prijs == null) {
			if (other.prijs != null) {
				return false;
			}
		} else if (!prijs.equals(other.prijs)) {
			return false;
		}
		if (soort == null) {
			if (other.soort != null) {
				return false;
			}
		} else if (!soort.equals(other.soort)) {
			return false;
		}
		return true;
	}
	
}
