package be.vdab.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Entity
@Table(name="bestelbonnen")
public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private LocalDateTime besteld;
	private String naam;
	private Adres adres;
	@Enumerated(EnumType.ORDINAL)
	private Bestelwijze bestelwijze;
	@Version
	private long versie;
	@ElementCollection
	@CollectionTable(name="bestelbonlijnen", joinColumns = @JoinColumn(name="bonid"))
	private Set<Bestelbonlijn> bestelbonlijnen;
	
	protected Bestelbon() {}
	
	public Bestelbon(String naam, Adres adres, Bestelwijze bestelwijze) {
		besteld = LocalDateTime.now();
		if (isNaamValid(naam)) {
			this.naam = naam;
		}
		this.adres = adres;
		this.bestelwijze = bestelwijze;
		bestelbonlijnen=new LinkedHashSet<>();
	}
	
	public void addBestelbonlijn(Bestelbonlijn bestelbonlijn) {
		if (bestelbonlijn.getBestelbon().equals(this)) {
			bestelbonlijnen.add(bestelbonlijn);
		}
	}
	
	public static boolean isNaamValid(String naam) {
		return naam != null && ! naam.trim().isEmpty();
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getBesteld() {
		return besteld;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public Bestelwijze getBestelwijze() {
		return bestelwijze;
	}

	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((besteld == null) ? 0 : besteld.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		if (!(obj instanceof Bestelbon)) {
			return false;
		}
		Bestelbon other = (Bestelbon) obj;
		if (besteld == null) {
			if (other.besteld != null) {
				return false;
			}
		} else if (!besteld.equals(other.besteld)) {
			return false;
		}
		if (naam == null) {
			if (other.naam != null) {
				return false;
			}
		} else if (!naam.equals(other.naam)) {
			return false;
		}
		return true;
	}
	
	
}
