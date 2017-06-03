package io.github.mistercavespider.lina;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

public class LineArray extends Mesh implements Lina {

	protected int size;
	
	protected Vector3f defVertex = Vector3f.ZERO;
	protected ColorRGBA defColor = ColorRGBA.White;
	
	protected Vector3f[] points;
	protected ColorRGBA[] colors;
	
	public LineArray(int size) {
		this.size = size;
		
		points = new Vector3f[size];
		colors = new ColorRGBA[size];
		
		//initial update
		setMode(Mode.Lines);
		setAllBuffers();
	}
	
	@Override
	public void setAllBuffers() {
		setIndexBuffer();
		setVertexBuffer();
		setColorBuffer();
	}
	
	@Override
	public void setIndexBuffer() {
		int[] indices = new int[size*2-2];
		int i = 0;
		
		/*
		 * If the total size is 8, the last entries
		 * would be [ ... , 5,6, 6,7 ];
		 */
		for (int j = 0; j < size-1; j++) {
			indices[i++] = j;
			indices[i++] = j+1;
		}
		
		setBuffer(Type.Index, 2, indices);
		//System.out.println(Arrays.toString(indices));
	}
	
	@Override
	public void setVertexBuffer() {
		getVertex(size-1);
		setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(points));
		//System.out.println(Arrays.toString(points));
	}

	@Override
	public void setColorBuffer() {
		getColor(size-1);
		setBuffer(Type.Color, 4, BufferUtils.createFloatBuffer(colors));
		//System.out.println(Arrays.toString(colors));
	}
	
	public Vector3f getVertex(int index) {
		if(points[index] == null) {
			if(index == 0) {
				points[index] = Vector3f.ZERO;
			} else {
				points[index] = getVertex(index-1);
			}
		}
		return points[index];
	}
	
	public LineArray setVertex(int index, Vector3f vertex) {
		points[index] = vertex;
		setAllBuffers();
		return this;
	}
	
	public ColorRGBA getColor(int index) {
		if(colors[index] == null) {
			if(index == 0) {
				colors[index] = ColorRGBA.White;
			} else {
				colors[index] = getColor(index-1);
			}
		}
		return colors[index];
	}
	
	public LineArray setColor(int index, ColorRGBA color) {
		colors[index] = color;
		setAllBuffers();
		return this;
	}
	
	public int getSize() {
		return size;
	}
}
