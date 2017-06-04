package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

public abstract class DoubleColorController implements ColorController {

	protected ColorRGBA baseColor, secondaryColor;
	protected int maxSize;
	
	@Override
	public void setBaseColor(ColorRGBA baseColor) {
		this.baseColor = baseColor;
	}
	
	public void setSecondaryColor(ColorRGBA secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	@Override
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

}
