package ar.com.codoacodo.interfaces;

public class EmailLogger implements ILogger {

	@Override
	public void log() {
		
		System.out.println("Enviando un mail a mail@mail.com");

	}

}
