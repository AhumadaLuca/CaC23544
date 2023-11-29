package ar.com.codoacodo.interfaces;

public class SMSLogger implements ILogger {
	
	//atributos? si
	private String number;
	
	//Constructor
	/*
	public SMSLogger(String number) {
		this.number = number;
	}
*/
	@Override
	public void log() {
		
		System.out.println("Enviando sms al: " + getNumber());

	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return this.number;
	}

}
