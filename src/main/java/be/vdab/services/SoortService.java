package be.vdab.services;

import java.util.List;

import be.vdab.entities.Soort;
import be.vdab.repositories.SoortRepository;

public class SoortService extends AbstractServices {
	private final transient SoortRepository soortRepository = new SoortRepository();
	
	public List<Soort> findByLandId(long landId) {
		return soortRepository.findByLand(landId);
	}

}
