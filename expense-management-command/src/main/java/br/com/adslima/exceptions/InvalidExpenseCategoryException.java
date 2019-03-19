/**
 * 
 */
package br.com.adslima.exceptions;

/**
 * @author andrews.silva
 *
 */
public class InvalidExpenseCategoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpenseCategoryException(String message) {
		super(message);
	}

}
