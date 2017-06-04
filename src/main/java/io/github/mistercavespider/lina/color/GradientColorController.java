package io.github.mistercavespider.lina.color;

import com.jme3.math.ColorRGBA;

public class GradientColorController extends DoubleColorController {
	
	@Override
	public ColorRGBA getColor(Object... args) {
		int index = (Integer) args[0];
		
		float scalar = ((float)index)/((float)maxSize);
		
		return baseColor.mult(scalar).add(secondaryColor.mult(1-scalar));
	}

}
