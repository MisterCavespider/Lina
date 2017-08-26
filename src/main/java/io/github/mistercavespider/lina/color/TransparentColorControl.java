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
	 * Takes a relative position as only argument.
	 * Must be a float between 0.0 and 1.0.
	 * 
	 * Outputs the baseColor with a certain transparency.
	 * The transparency is equal to the first argument.
	 * 
	 * <HR>
	 * 
	 * {@inheritDoc}
	 */
	public ColorRGBA getColor(Object... args) {
		return baseColor.mult( new ColorRGBA(1, 1, 1, (Float)args[0] ));
	}
	
}
