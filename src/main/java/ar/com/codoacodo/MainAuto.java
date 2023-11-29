package ar.com.codoacodo;

public class MainAuto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Auto clio = new Auto("Renault", "clio", "azul", 2020, "abc123", 3f);
		
		clio.encender();
		clio.acelerar();
		clio.frenar();
		clio.apagar();
		clio.frenar();

	}

}
