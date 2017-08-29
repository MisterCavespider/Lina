package io.github.mistercavespider.lina.ctrl;

import com.jme3.material.Material;

import io.github.mistercavespider.lina.LineString;

/**
 * Under development.
 * Marked as deprecated because of it.
 * 
 * @author MisterCavespider
 *
 */
@Deprecated
public class DistanceTracer implements Tracer {

	private Material mat;
	private LineString str;
	
	@Override
	public Material getMaterial() {
		return mat;
	}

	@Override
	public void setMaterial(Material mat) {
		this.mat = mat;
	}

	@Override
	public LineString getLineString() {
		return str;
	}

	@Override
	public void setLineString(LineString str) {
		this.str = str;
	}

	@Override
	public void reset() {
		
	}

}
