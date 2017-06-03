package io.github.mistercavespider.lina;

import java.util.Arrays;
import java.util.LinkedList;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

public class LineString extends Mesh implements Lina {

	protected int maxSize;
	
	protected LinkedList<Vector3f> vertices = new LinkedList<>();
	protected LinkedList<ColorRGBA> colors = new LinkedList<ColorRGBA>();
	
	public LineString(int maxSize) {
		this.maxSize = maxSize;
		
		setAllBuffers();
		setMode(Mode.Lines);
	}
	
	public LineString addPoint(Vector3f vertex, ColorRGBA color) {
		vertices.add(vertex);
		while(maxSize > 0 && vertices.size() > maxSize) {
			vertices.removeFirst();
		}
		
		colors.add(color);
		while(maxSize > 0 && colors.size() > maxSize) {
			colors.removeFirst();
		}
		
		setAllBuffers();
		return this;
	}
	
	@Override
	public void setAllBuffers() {
		setIndexBuffer();
		setVertexBuffer();
		setColorBuffer();
	}

	@Override
	public void setIndexBuffer() {
		int size = vertices.size();
		
		int[] indices;
		
		if(size < 1) {
			return;
		} else if (size == 1) {
			indices = new int[] {0,0};
		} else {
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
		
		System.out.println(Arrays.toString(indices));
	}

	@Override
	public void setVertexBuffer() {
		Vector3f[] arrvertices = new Vector3f[vertices.size()];
		vertices.toArray(arrvertices);
		setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(arrvertices));
		
		System.out.println(Arrays.toString(arrvertices));
	}

	@Override
	public void setColorBuffer() {
		ColorRGBA[] arrcolors = new ColorRGBA[colors.size()];
		colors.toArray(arrcolors);
		
		System.out.println(Arrays.toString(arrcolors));
		setBuffer(Type.Color, 4, BufferUtils.createFloatBuffer(arrcolors));
	}

}
