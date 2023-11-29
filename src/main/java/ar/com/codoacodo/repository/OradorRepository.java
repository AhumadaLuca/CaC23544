package ar.com.codoacodo.repository;

import java.util.List;

import ar.com.codoacodo.entity.Orador;

public interface OradorRepository {
	
	public void save(Orador orador); //Guarda un orador
	
	public Orador getById(Long id); //Busca un orador por id
	
	public void update(Orador orador); //Busca y actualiza un orador
	
	public void delete(Long id); //Busca y elimina un orador
	
	public List<Orador> findAll(); //Busca y enlista todos los oradores

}
