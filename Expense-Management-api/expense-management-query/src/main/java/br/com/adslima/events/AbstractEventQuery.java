package br.com.adslima.events;

import java.io.Serializable;

/**
 * @author andrews.silva
 *
 */
public abstract class AbstractEventQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	public AbstractEventQuery() {
		}

	public AbstractEventQuery(String id) {
			this.id = id;
		}

	public String getId() {
		return id;
	}
}
