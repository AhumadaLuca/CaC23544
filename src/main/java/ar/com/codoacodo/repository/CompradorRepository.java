package ar.com.codoacodo.repository;

import java.util.List;

import ar.com.codoacodo.entity.Comprador;

public interface CompradorRepository {
	
	public void save(Comprador comprador); //Guarda un comprador
	
	public Comprador getById(Long id); //Busca un comprador por id
	
	public void update(Comprador comprador); //Busca y actualiza un comprador
	
	public void delete(Long id); //Busca y elimina un comprador
	
	public List<Comprador> findAll(); //Busca y enlista todos los comprador

}
