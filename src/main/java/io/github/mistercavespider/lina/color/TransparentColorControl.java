package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

/**
 * Let's the trail get progressively less
 * visible (transparent).
 * 
 * @author MisterCavespider
 *
 */
public class TransparentColorControl extends SingleColorController {

	/**
	 * Takes the index of the vertex as argument.
	 * Must be an integer.
	 * 
	 * Outputs the baseColor with a certain transparency.
	 * The transparency is calculated using index/(maxSize-1).
	 * 
	 * <HR>
	 * 
	 * {@inheritDoc}
	 */
	public ColorRGBA getColor(Object... args) {
		return baseColor.mult( new ColorRGBA(1, 1, 1, ((Integer)args[0]).floatValue()/((float)maxSize-1f) ));
	}
	
}
