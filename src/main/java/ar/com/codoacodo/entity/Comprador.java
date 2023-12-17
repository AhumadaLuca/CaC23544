package ar.com.codoacodo.entity;

import java.time.LocalDate;

public class Comprador {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private Long cantidad;
	private String categoria;
	private Long precioTotal;
	private LocalDate fechaCompra;
	
	//Esto lo usamos para enviarlo a la Base de Datos
	public Comprador(String nombre, String apellido, String email, Long cantidad, String categoria, Long precioTotal, LocalDate fechaCompra) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
	}

	public Comprador(Long id, String nombre, String apellido, String email, Long cantidad, String categoria,
			Long precioTotal, LocalDate fechaCompra) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Long precioTotal) {
		this.precioTotal = precioTotal;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Override
	public String toString() {
		return "Comprador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", cantidad=" + cantidad + ", categoria=" + categoria + ", precioTotal=" + precioTotal
				+ ", fechaCompra=" + fechaCompra + "]";
	}

	
	
	
	

}
