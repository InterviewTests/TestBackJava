package br.com.adslima.event;

import java.io.Serializable;

/**
 * 
 * @author andrews.silva
 *
 */
public abstract class AbstractCommunsEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	public AbstractCommunsEvent() {
	}

	public AbstractCommunsEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}