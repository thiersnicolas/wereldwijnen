package be.vdab.services;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import be.vdab.entities.Bestelbon;
import be.vdab.exceptions.BestelbonBestaatAlException;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.exceptions.WijnBestaatNietException;
import be.vdab.repositories.BestelbonRepository;
import be.vdab.repositories.WijnRepository;
import be.vdab.valueobjects.Bestelbonlijn;

public class BestelbonService extends AbstractServices{
	private final transient BestelbonRepository bestelbonRepository = new BestelbonRepository();
	private final transient WijnRepository wijnRepository = new WijnRepository();
	
	public void createBestelbon(Bestelbon bestelbon) {
		beginTransaction();
		try {
			if (!(bestelbonRepository.read(bestelbon.getId()).isPresent())) {
				for (Bestelbonlijn bestelbonlijn:bestelbon.getBestelbonlijnen()) {
					if (!(wijnRepository.read(bestelbonlijn.getWijn().getId()).isPresent())){
						throw new WijnBestaatNietException();
					}
				}
				bestelbonRepository.createBestelbon(bestelbon);
				
				commit();
			} else throw new BestelbonBestaatAlException();
		} catch (RollbackException ex) {
			if (ex.getCause() instanceof OptimisticLockException) {
				throw new RecordAangepastException();
			}
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
}
