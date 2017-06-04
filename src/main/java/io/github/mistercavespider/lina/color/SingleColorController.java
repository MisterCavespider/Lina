package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

public abstract class SingleColorController implements ColorController {

	protected ColorRGBA baseColor;
	protected int maxSize;
	
	@Override
	public void setBaseColor(ColorRGBA baseColor) {
		this.baseColor = baseColor;
	}
	@Override
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
}
