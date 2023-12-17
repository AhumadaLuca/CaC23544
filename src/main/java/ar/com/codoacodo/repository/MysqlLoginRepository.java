package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ar.com.codoacodo.entity.Usuario;

public class MysqlLoginRepository implements LoginRepository {

	@Override
	public Usuario login(String username, String password) {
		
		//Aca usariamos una tabla llamada "users" y ahi nos comunicamos
		String sql = "select * from users where username = ? and password = ?";

		Usuario entity = null;
		
		try(Connection con = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet res = statement.executeQuery();// SELECT

			if (res.next()) {
				Long dbId = res.getLong(1);  
				String nombre = res.getString(2);  
				String dbUsername= res.getString(3);  
				String dbPassword = res.getString(4);  
				
				entity = new Usuario(dbId, nombre, dbUsername,dbPassword);
			}
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener usuario:", e);
		}
		return entity;

}
}
