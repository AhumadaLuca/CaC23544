package ar.com.codoacodo.repository;

import ar.com.codoacodo.entity.Usuario;

public interface LoginRepository {
	
	public Usuario login(String username, String password);

}
