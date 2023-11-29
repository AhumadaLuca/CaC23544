package ar.com.codoacodo.herencia;

public class MainHerencia {

	public static void main(String[] args) {
		
		//Creamos un Articulo
		Articulo a1 = new Articulo("titulo1", 150.5f, "url-de-la-imagen.jpg");
		
		//Llamo al setter de autor
		a1.setAutor("Autor Fulanito");
		
		//metodos disponibles
		System.out.println(a1);
		
		//Hijo h = new Hijo()
		Musica m = new Musica("m1", 250.7f,"url-de-la-imagen.jpg");
		
		m.setAutor("Autor Pepe"); //Esto es de Articulo
		
		//Creamos la lista de temas para el hijo Musica
		String[] temas = new String[] {"t1","t2","t3"};
		
		//Seteamos de temas
		m.setTemas(temas);
		
		//el hijo "m" tiene disponible lo suyo más todo del padre "Articulo"
		System.out.println(m);
		
		//Esto no funcionaria porque no accedemos a los atributos de pelicula
		Articulo p1 = new Pelicula("Titulo abc123", 858f, "imagen.jpg");
		p1.setAutor("Autor 123");
		//La pelicula dentro del articulo
		((Pelicula)p1).getAutor();
		
		//En cambio, hacemos "down casting"
		Pelicula peli = (Pelicula)p1;
		peli.setFormato("BLUE RAY 3D");
		peli.setProductora("WARNER BROS");
		
		//Vector para guardar las instancias
		Articulo[] resultados = new Articulo[3];

	}

}
