package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

public interface ColorController {

	public void setBaseColor(ColorRGBA baseColor);
	public void setMaxSize(int maxSize);
	
	public ColorRGBA getColor(Object... args);
	
}
