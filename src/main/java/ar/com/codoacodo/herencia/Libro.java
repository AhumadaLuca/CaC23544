package ar.com.codoacodo.herencia;

public class Libro extends Articulo {
	
	private String isbn;
	
	//Libro no puede nacer sí primero no nace el articulo

	public Libro(String titulo, Float precio, String img, String isbn) {
		
		//Super hace referencia de Padre
		super(titulo, precio, img); //Nace Articulo
		
		//Ahora puedo usar this.
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	//Asi agregamos el ToString del Padre, con el "super.ToString()"
	@Override
	public String toString() {
		return super.toString() + ", Libro [isbn=" + isbn + "]";
	}
	
	
	
}
