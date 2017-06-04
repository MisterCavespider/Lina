package io.github.mistercavespider.lina.ctrl;

import com.jme3.material.Material;

import io.github.mistercavespider.lina.LineString;

public interface Tracer {

	public Material getMaterial();
	public void setMaterial(Material mat);
	
	public LineString getLineString();
	public void setLineString(LineString str);
	
}
