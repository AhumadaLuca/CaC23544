package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.utils.DateUtils;

public class MysqlOradorRepository implements OradorRepository {

	@Override
	public void save(Orador orador) {

		// 2 : Preparo el sql - sql injection!
		String sql = "Insert into orador(nombre, apellido, mail, tema, fecha_alta) values(?,?,?,?,?)";

		// 3 : Preparo el statement

		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getMail());
			statement.setString(4, orador.getTema());
			statement.setDate(5, new java.sql.Date(DateUtils.asDate(orador.getFechaAlta()).getTime())); // tph: ver como
																										// pasar de
																										// LocalDate a
			// java.sql.Date

			statement.executeUpdate(); // INSERT/UPDATE/DELETE

			ResultSet res = statement.getGeneratedKeys();

			if (res.next()) {
				Long id = res.getLong(1); // aca esta el id
				orador.setId(id);
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador: ", e);
		}

	}

	@Override
	public Orador getById(Long id) {

		// 2 : Preparo el sql - sql injection!
		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from orador where id_orador = ?";

		// 3 : Preparo el statement
		Orador orador = null;

		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery(); // SELECT

			if (res.next()) {

				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String mail = res.getString(4);
				String tema = res.getString(5);
				Date fechaAlta = res.getDate(6);

				orador = new Orador(dbId, nombre, apellido, mail, tema, DateUtils.asLocalDate(fechaAlta));
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo buscar el orador: ", e);
		}

		return orador;

	}

	@Override
	public void update(Orador orador) {

		// 2 : Preparo el sql - sql injection!
		String sql = "update orador set nombre=?, apellido=?, mail=?, tema=?, where id = ?";

		// try with resources
		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getMail());
			statement.setString(4, orador.getTema());
			statement.setLong(5, orador.getId());

			statement.executeUpdate(); // SELECT

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo buscar el orador: ", e);
		}

	}

	@Override
	public void delete(Long id) {

		// 2 : Preparo el sql - sql injection!
		String sql = "delete from orador where id = ?";

		// try with resources
		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, id);

			statement.executeUpdate(); // SELECT

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo eliminar el orador: ", e);
		}
	}

	@Override
	public List<Orador> findAll() {

		// 2 : Preparo el sql - sql injection!
		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from orador";

		// 3 : Preparo el statement
		List<Orador> oradores = new ArrayList<>(); // se ve bien en spring

		// try with resources
		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet res = statement.executeQuery(); // SELECT

			while (res.next()) {

				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String mail = res.getString(4);
				String tema = res.getString(5);
				LocalDate fechaAlta = DateUtils.asLocalDate(res.getDate(6));

				oradores.add(new Orador(dbId, nombre, apellido, mail, tema, LocalDate.now()));

			}

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo buscar el orador: ", e);
		}

		return oradores;
	}

}
