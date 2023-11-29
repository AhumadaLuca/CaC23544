package ar.com.codoacodo.interfaces;

public class FSLogger implements ILogger {
	
	//implementamos el metodo log
	//polimorfismo: sobre-escritura
	public void log() {
		System.out.println("Grabando en c:/bla");
	};

}
