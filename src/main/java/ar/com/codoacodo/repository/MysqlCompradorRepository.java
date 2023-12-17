package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Comprador;
import ar.com.codoacodo.utils.DateUtils;

public class MysqlCompradorRepository implements CompradorRepository{
	
	@Override
	public void save(Comprador comprador) {

		// 2 : Preparo el sql - sql injection!
		String sql = "Insert into comprador(nombre, apellido, email, cantidad, categoria, precio_total, fecha_compra) values(?,?,?,?,?,?,?)";

		// 3 : Preparo el statement

		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, comprador.getNombre());
			statement.setString(2, comprador.getApellido());
			statement.setString(3, comprador.getEmail());
			statement.setLong(4, comprador.getCantidad());
			statement.setString(5, comprador.getCategoria());
			statement.setLong(6, comprador.getPrecioTotal());
			statement.setDate(7, new java.sql.Date(DateUtils.asDate(comprador.getFechaCompra()).getTime()));
			// java.sql.Date

			statement.executeUpdate(); // INSERT/UPDATE/DELETE

			ResultSet res = statement.getGeneratedKeys();

			if (res.next()) {
				Long id = res.getLong(1); // aca esta el id
				comprador.setId(id);
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo crear el comprador: ", e);
		}

	}

	@Override
	public Comprador getById(Long id) {

		// 2 : Preparo el sql - sql injection!
		String sql = "select id_comprador, nombre, apellido, email, cantidad, categoria, precio_total, fecha_compra from comprador where id_comprador = ?";

		// 3 : Preparo el statement
		Comprador comprador = null;

		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery(); // SELECT

			if (res.next()) {

				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String email = res.getString(4);
				Long cantidad = res.getLong(5);
				String categoria = res.getString(6);
				Long precioTotal = res.getLong(7);
				Date fechaCompra = res.getDate(8);
				

				comprador = new Comprador(dbId, nombre, apellido, email, cantidad, categoria, precioTotal, DateUtils.asLocalDate(fechaCompra));
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo buscar el comprador: ", e);
		}

		return comprador;

	}

	@Override
	public void update(Comprador comprador) {

		// 2 : Preparo el sql - sql injection!
		String sql = "update comprador "
				+ "set nombre=?, apellido=?, email=?, cantidad=?, categoria=?, precio_total=? "
				+ "where id_comprador = ?";

		// try with resources
		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, comprador.getNombre());
			statement.setString(2, comprador.getApellido());
			statement.setString(3, comprador.getEmail());
			statement.setLong(4, comprador.getCantidad());
			statement.setString(5, comprador.getCategoria());
			statement.setLong(6, comprador.getPrecioTotal());
			statement.setLong(7, comprador.getId());

			statement.executeUpdate(); // SELECT

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo buscar el comprador: ", e);
		}

	}

	@Override
	public void delete(Long id) {

		// 2 : Preparo el sql - sql injection!
		String sql = "delete from comprador where id_comprador = ?";

		// try with resources
		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setLong(1, id);

			statement.executeUpdate();

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo eliminar el comprador: ", e);
		}
	}

	@Override
	public List<Comprador> findAll() {

		// 2 : Preparo el sql - sql injection!
		String sql = "select id_comprador, nombre, apellido, email, cantidad, categoria, precio_total from comprador";

		// 3 : Preparo el statement
		List<Comprador> compradores = new ArrayList<>(); // se ve bien en spring

		// try with resources
		// Colocando la apertura de la conexion acá, nos ahorramos de cerrarla nosotros
		try (Connection con = AdministradorDeConexiones.getConnection()) {

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet res = statement.executeQuery(); // SELECT

			while (res.next()) {

				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String email = res.getString(4);
				Long cantidad = res.getLong(5);
				String categoria = res.getString(6);
				Long precioTotal = res.getLong(7);

				compradores.add(new Comprador(dbId, nombre, apellido, email, cantidad, categoria, precioTotal, LocalDate.now()));

			}

		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo buscar el comprador: ", e);
		}

		return compradores;
	}

}


