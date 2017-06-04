package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

/**
 * Decides the color of a vertex based on several arguments.
 * 
 * @author MisterCavespider
 *
 */
public interface ColorController {

	/**
	 * Sets the base color. This is the primary color used.
	 * @param baseColor	The primary color to use.
	 */
	public void setBaseColor(ColorRGBA baseColor);
	/**
	 * Sets the maximum size of the buffer. This data
	 * is useful to know when making e.g. gradients.
	 * @param maxSize
	 */
	public void setMaxSize(int maxSize);
	
	/**
	 * Requests a color based on several arguments.
	 * These arguments can vary from class to class.
	 * 
	 * @param args	Several arguments.
	 * @return		A color, based on the arguments.
	 */
	public ColorRGBA getColor(Object... args);
	
}
