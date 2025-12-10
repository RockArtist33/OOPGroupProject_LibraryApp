package bcu.k9.librarysystem.main;

/**
 * InvalidDateException extends {@link Exception} class and is a custom exception.
 * It is used to identify if a date and time is invalid for a function's specified purpose.
 * Usually appears if the specified date is outside of the bounds of the function.
 */

public class InvalidDateException extends Exception{
	public InvalidDateException(String message) {
		super(message);
	}

}
