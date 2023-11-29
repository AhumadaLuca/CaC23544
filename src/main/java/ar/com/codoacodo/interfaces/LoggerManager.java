package ar.com.codoacodo.interfaces;

public class LoggerManager {
	
	public static ILogger getLooger(String target) {
		/*
		 * A > FSLogger
		 * B > EmailLogger
		 * C > SMSLogger
		 */
		
		ILogger logger;
		
		switch(target) {
		case "A":
			logger = new SMSLogger();
			System.out.println("Ingrese un numero");
			((SMSLogger)logger).setNumber("123456789");
			break;
		case "B":
			logger = new FSLogger();
			break;
		case "C":
			logger = new EmailLogger();
			break;
			default:
				logger = null;
		}
		
		return logger;
	}

}
