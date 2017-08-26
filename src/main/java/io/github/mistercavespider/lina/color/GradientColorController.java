package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

/**
 * Outputs a gradient based on index.
 * The color at the end (low index) is secondary,
 * the color at the beginning (high index) is primary).
 * 
 * @author MisterCavespider
 *
 */
public class GradientColorController extends DoubleColorController {
	
	
	/**
	 * Takes a relative position as only argument.
	 * Must be a float between 0.0 and 1.0.
	 * 
	 * <HR>
	 * 
	 * {@inheritDoc}
	 */
	public ColorRGBA getColor(Object... args) {
		float scalar = (Float) args[0];
		return baseColor.mult(scalar).add(secondaryColor.mult(1-scalar));
	}

}
