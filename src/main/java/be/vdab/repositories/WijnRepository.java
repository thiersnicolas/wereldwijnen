package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Wijn;

public class WijnRepository extends AbstractRepository {
	public List<Wijn> findBySoort(long soortId){
		return getEntityManager().createNamedQuery("Wijn.findBySoortNaam", Wijn.class)
				.setParameter("id", soortId)
				.getResultList();
	}
	
	public Optional<Wijn> read(long id){
		return Optional.ofNullable(getEntityManager().find(Wijn.class, id));
	}
}
