package console;

public class CommandNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1951536045993859838L;

	String szIssuedCommand = "no command";
	public CommandNotFoundException(String szCommand) {
		// TODO Auto-generated constructor stub
		szIssuedCommand = szCommand;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		
		return "Unknown command: " + szIssuedCommand;
	}
	
	
	
}
