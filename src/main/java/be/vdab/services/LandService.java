package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Land;
import be.vdab.repositories.LandRepository;

public class LandService extends AbstractServices {
	private final transient LandRepository landRepository = new LandRepository();
	
	public List<Land> findAll(){
		return landRepository.findAll();
	}
	
	public Optional<Land> read(long id){
		return landRepository.read(id);
	}
}
