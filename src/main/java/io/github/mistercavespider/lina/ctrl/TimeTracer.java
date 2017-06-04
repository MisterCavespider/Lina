package io.github.mistercavespider.lina.ctrl;

import java.util.LinkedList;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

import io.github.mistercavespider.lina.LineString;

/**
 * Traces the Spatial. Updates the position based on time.
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
	 * Simplest constructor. Assumes an updateTime of 200,
	 * and a maxSize of 64.
	 * 
	 * @param mat	Material applied to LineString
	 */
	public TimeTracer(Material mat) {
		this(mat, 500, 64);
	}
	
	/**
	 * Constructor. Assumes a maxSize of 64.
	 * @param mat			Material applied to LineString
	 * @param updateTime	The amount of time to wait before updating
	 */
	public TimeTracer(Material mat, long updateTime) {
		this(mat, updateTime, 64);
	}
	
	/**
	 * Constructor. Assumes an updateTime of 200.
	 * @param mat		Material applied to LineString
	 * @param maxSize	The maximum size to use for the LineString
	 */
	public TimeTracer(Material mat, int maxSize) {
		this(mat, 200, maxSize);
	}
	
	/**
	 * Default constructor.
	 * Constructs LineString on its own.
	 * @param mat			Material applied to LineString
	 * @param updateTime	The amount of time to wait before updating
	 * @param maxSize		The maximum size to use for the LineString
	 */
	public TimeTracer(Material mat, long updateTime, int maxSize) {
		this(mat, updateTime, new LineString(maxSize));
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
		mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		strGeom.setQueueBucket(Bucket.Transparent);
		spatial.getParent().attachChild(strGeom);
	}

	@Override
	protected void controlUpdate(float tpf) {
		LinkedList<Vector3f> vertices = str.getVertices();
		if(vertices.size() > 1) {
			vertices.set(vertices.size()-1, getSpatial().getLocalTranslation().clone());
			str.setAllBuffers();
		}
		
		if(System.currentTimeMillis() - last > updateTime) {
			str.addPoint(getSpatial().getLocalTranslation().clone());
			strGeom.updateModelBound();
			last = System.currentTimeMillis();
		}
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}

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
