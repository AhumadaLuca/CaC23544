package ar.com.codoacodo.entity;

public class Usuario {
	
	private Long id;
	private String nombre;
	private String username;
	private String password;
	public Usuario(Long id, String nombre, String username, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", password=" + password + "]";
	}
	
	

}
