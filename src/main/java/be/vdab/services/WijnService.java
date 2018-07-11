package be.vdab.services;

import java.util.List;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService extends AbstractServices {
	private final transient WijnRepository wijnRepository = new WijnRepository();
	
	public List<Wijn> findBySoortId(long soortId){
		return wijnRepository.findBySoort(soortId);
	}
}
