package Exceptions;

public class IdNotFoundException extends Exception {

	static final long serialVersionUID = 1L;

	public IdNotFoundException (String msg) {
		
		super(msg);
	}
}
