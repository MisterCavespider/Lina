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
	 * Takes the index of the vertex as argument.
	 * Must be an integer.
	 * 
	 * <HR>
	 * 
	 * {@inheritDoc}
	 */
	public ColorRGBA getColor(Object... args) {
		int index = (Integer) args[0];
		
		float scalar = ((float)index)/((float)maxSize);
		
		return baseColor.mult(scalar).add(secondaryColor.mult(1-scalar));
	}

}
