package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Soort;

public class SoortRepository extends AbstractRepository {
	public List<Soort> findByLand(long landId){
		return getEntityManager().createNamedQuery("Soort.findByLandId", Soort.class)
				.setParameter("id", landId)
				.getResultList();
	}
	
	public Optional<Soort> read(long id){
		return Optional.ofNullable(getEntityManager().find(Soort.class, id));
	}
}
