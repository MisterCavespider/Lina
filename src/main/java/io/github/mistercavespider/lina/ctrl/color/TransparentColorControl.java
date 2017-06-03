package io.github.mistercavespider.lina.ctrl.color;

import com.jme3.math.ColorRGBA;

public class TransparentColorControl implements ColorController {

	private ColorRGBA baseColor;
	private int maxSize;
	
	public TransparentColorControl(int maxSize) {
		this.maxSize = maxSize;
	}
	
	@Override
	public ColorRGBA getColor(Object... args) {
		return baseColor.mult( new ColorRGBA(1, 1, 1, ((Integer)args[0]).floatValue()/((float)maxSize) ));
	}

	@Override
	public void setBaseColor(ColorRGBA baseColor) {
		this.baseColor = baseColor;
	}

	@Override
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
}
