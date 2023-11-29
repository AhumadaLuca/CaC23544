package ar.com.codoacodo.interfaces;

//Un contrato (que parece una clase) que contiene solo metodos y constantes (final)
public interface interfaces {
	
	final String hola = "";
	
	public void metodo1(); //Definicion de un metodo que no tiene cuerpo
	
	public default void metodo2() {
		System.out.println("Metodo2");
	}

}
