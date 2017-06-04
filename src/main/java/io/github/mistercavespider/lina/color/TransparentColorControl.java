package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

public class TransparentColorControl extends SingleColorController {

	@Override
	public ColorRGBA getColor(Object... args) {
		return baseColor.mult( new ColorRGBA(1, 1, 1, ((Integer)args[0]).floatValue()/((float)maxSize) ));
	}
	
}
