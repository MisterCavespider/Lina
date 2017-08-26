package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

/**
 * An abstract implementation of {@link ColorController}
 * with two colors.
 * 
 * @author MisterCavespider
 *
 */
public abstract class DoubleColorController implements ColorController {

	protected ColorRGBA baseColor, secondaryColor;
	
	@Override
	public void setBaseColor(ColorRGBA baseColor) {
		this.baseColor = baseColor;
	}
	
	/**
	 * Sets secondary color.
	 * @param secondaryColor	The secondary color to use.
	 */
	public void setSecondaryColor(ColorRGBA secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

}
