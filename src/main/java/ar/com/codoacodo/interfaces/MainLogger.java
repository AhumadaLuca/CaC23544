package ar.com.codoacodo.interfaces;

public class MainLogger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String target = getTargetFromDB();
		
		ILogger logger = LoggerManager.getLooger(target);
		
		logger.log();
	}
	
	public static String getTargetFromDB() {
		return "A";
	}

}
