/*
 * Licensed under the self-made Genus-Homo-License:
 * 
 * This license allows all animals of the genus "Homo" to
 * copy, modify, or redistribute
 * this program, library, license, or script.
 * 
 * ANY ANIMALS OTHER THAN THOSE BELOW ARE NOT PERMITTED TO USE, MODIFY, COPY OR DERIVE FROM THIS CODE.
 * PERMITTED SPECIES:
 * H. HABILIS, H. ERECTUS, H. RUDOLFENIS, H. GAUTENGENSIS, H. ERGASTER, H. ANTECESSOR, H. CEPRANENSIS, H. HEIDELBERGENSIS, H. NEANDERTHALIS, H. NALEDI, H. TSAICHANGENSIS, H.RHODESIENSIS, H. SAPIENS, H. FLORESIENSIS, WHERE "H." STANDS FOR "HOMO".
 * 
 * Get the license here:
 * https://github.com/MisterCavespider/Genus-Homo-License
 * 
 * Copyright (c) 2016 RunningChickenDev
 * Find me here: https://runningchickendev.github.io/
 * 
 */
package io.github.mistercavespider.lina.ctrl.color;

import com.jme3.math.ColorRGBA;

public class GradientColorController implements ColorController {

	private ColorRGBA baseColor, endColor;
	private int maxSize;
	
	@Override
	public void setBaseColor(ColorRGBA baseColor) {
		this.baseColor = baseColor;
	}
	
	public void setEndColor(ColorRGBA endColor) {
		this.endColor = endColor;
	}

	@Override
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public ColorRGBA getColor(Object... args) {
		int index = (Integer) args[0];
		
		float scalar = ((float)index)/((float)maxSize);
		
		return baseColor.mult(scalar).add(endColor.mult(1-scalar));
	}

}
