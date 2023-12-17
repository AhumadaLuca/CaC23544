package ar.com.codoacodo.controllers;

public class CompradorRequest {
	
	private String nombre;
	private String apellido;
	private String email;
	private Long cantidad;
	private String categoria;
	private Long precioTotal;
	
	//Lo necesita jackson para crearlo
	public CompradorRequest() {
	}

	public CompradorRequest(String nombre, String apellido, String email, Long cantidad, String categoria,
			Long precioTotal) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.precioTotal = precioTotal;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public Long getPrecioTotal() {
		return precioTotal;
	}

	@Override
	public String toString() {
		return "CompradorRequest [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", cantidad="
				+ cantidad + ", categoria=" + categoria + ", precioTotal=" + precioTotal + "]";
	}
	
	
	

}
