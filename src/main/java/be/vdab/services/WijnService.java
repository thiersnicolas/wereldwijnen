package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService extends AbstractServices {
	private final transient WijnRepository wijnRepository = new WijnRepository();
	
	public List<Wijn> findBySoortId(long soortId){
		return wijnRepository.findBySoort(soortId);
	}
	
	public Optional<Wijn> read(long id){
		return wijnRepository.read(id);
	}
}
