package ar.com.codoacodo.repository;

import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class MainRepository {

	public static void main(String[] args) {
		
		OradorRepository repo = new MysqlOradorRepository();
		
		//Obtendremos un orador desde la db
		Orador orador = repo.getById(1L);
		
		System.out.println(orador);
		
		List<Orador> listado = repo.findAll();
		
		System.out.println(listado);
		
		repo.delete(1L);
		
		List<Orador> listado2 = repo.findAll();
		
		System.out.println(listado2);

	}

}
