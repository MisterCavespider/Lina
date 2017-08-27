package io.github.mistercavespider.lina;

import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.List;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

import io.github.mistercavespider.lina.color.ColorController;
import io.github.mistercavespider.lina.color.GradientColorController;

/**
 * A string of lines. Has a variable length.
 * 
 * @author MisterCavespider
 *
 */
public class LineString extends Mesh implements Lina {

	protected int maxSize;
	
	protected LinkedList<Vector3f> vertices = new LinkedList<>();
	
	protected ColorController colorController;
	
	/**
	 * Assumes a maxSize of 64 and
	 * a colorController of null.
	 * 
	 * @see LineString#LineString(int, ColorController)
	 */
	public LineString() {
		this(64, null);
	}
	
	/**
	 * Assumes a colorController of null.
	 * @param maxSize	The maximum size of the lists.
	 */
	public LineString(int maxSize) {
		this(maxSize, null);
	}
	
	/**
	 * Full constructor.<br>
	 * The size maxSize is the maximum size
	 * the buffers can have.
	 * Do note that this does <b>not</b> mean
	 * the buffers (in the form of lists)
	 * start with that size, but that they
	 * can never get larger than it.<br>
	 * The colorController dictates what
	 * the colors look like.
	 * @see ColorController
	 * 
	 * @param maxSize
	 * @param colorController
	 */
	public LineString(int maxSize, ColorController colorController) {
		this.maxSize = maxSize;
		this.colorController = colorController;
		if(this.colorController == null) {
			this.colorController = new GradientColorController();
			this.colorController.setBaseColor(ColorRGBA.Red);
			((GradientColorController)this.colorController).setSecondaryColor(ColorRGBA.White);
		}
		
		setAllBuffers();
		setMode(Mode.Lines);
	}

	/**
	 * Adds a point (location) to the string.
	 * Also rewrites all buffers.
	 * 
	 * @param vertex	The point (location)
	 * @return this
	 */
	public LineString addPoint(Vector3f vertex) {
		vertices.add(vertex);
		cutInSize();
		
		setAllBuffers();
		return this;
	}
	
	public LineString addPoints(Vector3f... vertex_arr) {
		for(Vector3f v : vertex_arr) {
			vertices.add(v);
		}
		cutInSize();
		
		setAllBuffers();
		return this;
	}
	
	private void cutInSize() {
		while(maxSize > 0 && vertices.size() > maxSize) {
			vertices.removeFirst();
		}
	}
	
	@Override
	public void setAllBuffers() {
		setIndexBuffer();
		setVertexBuffer();
		setColorBuffer();
	}

	@Override
	public void setIndexBuffer() {
		if(vertices.isEmpty()) return;
		
		int size = vertices.size();
		int[] indices;
		
		if(size < 1) {	// 0 or negative
			return;
		} else if (size == 1) {	// 1
			/*
			 * This is not necessary
			 * The indices {0,0}
			 * will not show,
			 * and the buffer might as
			 * well not be sent, like
			 * with size < 1
			 */
			indices = new int[] {0,0};
		} else {	// 2 or more
			indices = new int[size*2-2];
			int i = 0;
			
			/*
			 * If the total size is 8, the last entries
			 * would be [ ... , 5,6, 6,7 ];
			 */
			for (int j = 0; j < size-1; j++) {
				indices[i++] = j;
				indices[i++] = j+1;
			}
		}
		
		setBuffer(Type.Index, 2, indices);
	}

	@Override
	public void setVertexBuffer() {
		if(vertices.isEmpty()) return;
		
		Vector3f[] arrvertices = new Vector3f[vertices.size()];
		vertices.toArray(arrvertices);
		setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(arrvertices));
	}

	@Override
	public void setColorBuffer() {
		if(vertices.isEmpty()) return;
		
		ColorRGBA[] colors = new ColorRGBA[vertices.size()];
		
		if(vertices.size() == 1) {
			colors[0] = colorController.getColor(1f);
		} else {
			for (int i = 0; i < colors.length; i++) {
				colors[i] = colorController.getColor(i/(vertices.size()-1f));
			}
		}
		
		setBuffer(Type.Color, 4, BufferUtils.createFloatBuffer(colors));
	}

	/**
	 * Gets the maximum size of the string.
	 * @return	The maximum size of the string
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * Sets the maximum size of the string.
	 * @param maxSize	The maximum size of the string
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	/**
	 * Directly calls {@link List#set(int, Object)}.
	 * Also checks if the list is empty.
	 * Does not update buffers.
	 * 
	 * @param index	index in the list
	 * @param v		the vertex that is put in the list
	 */
	public void setVertex(int index, Vector3f v) {
		if(vertices.isEmpty()) return;
		this.vertices.set(index, v);
	}
	
	/**
	 * Directly calls {@link List#set(int, Object)}
	 * with index = 0
	 * 
	 * @param v		the vertex that is put in the list
	 */
	public void setFirstVertex(Vector3f v) {
		setVertex(0, v);
	}
	
	/**
	 * Directly calls {@link List#set(int, Object)}
	 * with index = {@link List#size() size} - 1
	 * 
	 * @param v		the vertex that is put in the list
	 */
	public void setLastVertex(Vector3f v) {
		setVertex(vertices.size() -1, v);
	}
}
