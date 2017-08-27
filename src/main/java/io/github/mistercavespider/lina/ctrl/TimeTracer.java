package io.github.mistercavespider.lina.ctrl;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

import io.github.mistercavespider.lina.LineString;
import io.github.mistercavespider.lina.color.ColorController;

/**
 * Traces the Spatial.
 * Updates after <code>updateTime</code> has passed.
 * 
 * @author MisterCavespider
 *
 */
public class TimeTracer extends AbstractControl implements Tracer {

	private long last = 0;
	private long updateTime = 10;
	private Material mat;
	
	protected LineString str;
	protected Geometry strGeom;
	
	/**
	 * Simplest constructor allowed. Assumes an updateTime of 200,
	 * and a maxSize of 64.
	 * 
	 * @param mat	Material applied to {@link LineString}
	 */
	public TimeTracer(Material mat) {
		this(mat, 500, 64);
	}
	
	/**
	 * Assumes a maxSize of 64.
	 * @param mat			Material applied to {@link LineString}
	 * @param updateTime	The amount of time to wait before updating
	 */
	public TimeTracer(Material mat, long updateTime) {
		this(mat, updateTime, 64);
	}
	
	/**
	 * Assumes an updateTime of 200.
	 * @param mat		Material applied to {@link LineString}
	 * @param maxSize	The maximum size to use for the {@link LineString}
	 */
	public TimeTracer(Material mat, int maxSize) {
		this(mat, 200, maxSize);
	}
	
	/**
	 * Constructs LineString on its own.
	 * @param mat			Material applied to {@link LineString}
	 * @param updateTime	The amount of time to wait before updating
	 * @param maxSize		The maximum size to use for the {@link LineString}
	 */
	public TimeTracer(Material mat, long updateTime, int maxSize) {
		this(mat, updateTime, new LineString(maxSize));
	}
	
	/**
	 * Default constructor.
	 * Constructs LineString on its own.
	 * 
	 * @param mat			Material applied to {@link LineString}
	 * @param updateTime	The amount of time to wait before updating
	 * @param maxSize		The maximum size to use for the {@link LineString}
	 * @param cc			The {@link ColorController} used be the {@link LineString}
	 */
	public TimeTracer(Material mat, long updateTime, int maxSize, ColorController cc) {
		this(mat, updateTime, new LineString(maxSize, cc));
	}
	
	/**
	 * Default (full) constructor.
	 * @param mat			Material applied to {@link LineString}
	 * @param updateTime	The amount of time to wait before updating
	 * @param str			The {@link LineString}
	 */
	public TimeTracer(Material mat, long updateTime, LineString str) {
		this.mat = mat;
		this.updateTime = updateTime;
		this.str = str;
	}

	@Override
	public void setSpatial(Spatial spatial) {
		super.setSpatial(spatial);
		
		if(spatial == null) {
			strGeom.removeFromParent();
			return;
		}
		
		// This seems like a smart way to do this
		
		strGeom = new Geometry("LineString@"+spatial.getName(), str);
		strGeom.setMaterial(mat);
		mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		strGeom.setQueueBucket(Bucket.Transparent);
		spatial.getParent().attachChild(strGeom);
	}

	@Override
	protected void controlUpdate(float tpf) {
		str.setLastVertex(getSpatial().getLocalTranslation().clone());
		str.setAllBuffers();
		
		if(System.currentTimeMillis() - last > updateTime) {
			str.addPoint(getSpatial().getLocalTranslation().clone());
			strGeom.updateModelBound();
			last = System.currentTimeMillis();
		}
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}
	
	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

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
}
