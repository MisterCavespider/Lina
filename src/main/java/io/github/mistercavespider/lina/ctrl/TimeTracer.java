package io.github.mistercavespider.lina.ctrl;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

import io.github.mistercavespider.lina.LineString;

public class TimeTracer extends AbstractControl {

	private long last = 0;
	private long updateTime = 10;
	private Material mat;
	
	protected LineString str;
	protected Geometry strGeom;
	
	public TimeTracer(Material mat) {
		this(mat, 500, 64);
	}
	
	public TimeTracer(Material mat, long updateTime) {
		this(mat, updateTime, 64);
	}
	
	public TimeTracer(Material mat, int maxSize) {
		this(mat, 500, maxSize);
	}
	
	/**
	 * Default constructor.
	 * Constructs LineString on its own.
	 * @param mat			Material applied to LineString
	 * @param updateTime	The amount of time to wait before updating
	 * @param maxSize		The maximum size to use for the LineString
	 */
	public TimeTracer(Material mat, long updateTime, int maxSize) {
		this.mat = mat;
		this.updateTime = updateTime;
		this.str = new LineString(maxSize);
	}
	
	/**
	 * Default (full) constructor.
	 * @param mat			Material applied to LineString
	 * @param updateTime	The amount of time to wait before updating
	 * @param str			The LineString
	 */
	public TimeTracer(Material mat,long updateTime, LineString str ) {
		this.mat = mat;
		this.updateTime = updateTime;
		this.str = str;
	}



	@Override
	public void setSpatial(Spatial spatial) {
		super.setSpatial(spatial);
		
		//This seems like a dodgy way to do this
		
		strGeom = new Geometry("LineString@"+spatial.getName(), str);
		strGeom.setMaterial(mat);
		spatial.getParent().attachChild(strGeom);
	}

	@Override
	protected void controlUpdate(float tpf) {
		if(System.currentTimeMillis() - last > updateTime) {
			str.addPoint(getSpatial().getLocalTranslation().clone(), ColorRGBA.Green);
			strGeom.updateModelBound();
			last = System.currentTimeMillis();
		}
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}
}
