package Exceptions;

public class WrongArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongArgumentException (String msg) {
		
		super(msg);
	}
	
}
