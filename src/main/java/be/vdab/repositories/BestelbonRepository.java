package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Bestelbon;

public class BestelbonRepository extends AbstractRepository {
	
	public void createBestelbon(Bestelbon bestelbon) {
		getEntityManager().persist(bestelbon);
	}
	
	public Optional<Bestelbon> read(long id){
		return Optional.ofNullable(getEntityManager().find(Bestelbon.class, id));
	}
}
