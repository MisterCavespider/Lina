package io.github.mistercavespider.lina;

import io.github.mistercavespider.lina.color.ColorController;

/**
 * Basic interface for all Lines.
 * @author MisterCavespider
 *
 */
public interface Lina {

	/**
	 * Sets all buffers.
	 * Typically calls {@link #setIndexBuffer()},
	 * {@link #setVertexBuffer()}, and {@link #setColorBuffer()}.
	 */
	public void setAllBuffers();
	/**
	 * Sets the buffer for the indices.
	 * This is often [0,1, 1,2, ... n,n+1].
	 */
	public void setIndexBuffer();
	/**
	 * Sets the buffer for the vertices.
	 * In this function, any lists should be
	 * converted to arrays.
	 */
	public void setVertexBuffer();
	/**
	 * Sets the buffer for the colors.
	 * This probably includes calls to a
	 * {@link ColorController}.
	 */
	public void setColorBuffer();
	
}
