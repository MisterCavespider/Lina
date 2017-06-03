package io.github.mistercavespider.lina;

import java.util.Arrays;
import java.util.LinkedList;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

import io.github.mistercavespider.lina.ctrl.color.ColorController;
import io.github.mistercavespider.lina.ctrl.color.GradientColorController;

public class LineString extends Mesh implements Lina {

	protected int maxSize;
	
	protected LinkedList<Vector3f> vertices = new LinkedList<>();
	
	protected ColorController colorController;
	
	public LineString(int maxSize) {
		this.maxSize = maxSize;
		colorController = new GradientColorController();
		colorController.setBaseColor(ColorRGBA.randomColor());
		colorController.setMaxSize(64);
		((GradientColorController)colorController).setEndColor(ColorRGBA.randomColor());
		
		setAllBuffers();
		setMode(Mode.Lines);
	}
	
	public LineString addPoint(Vector3f vertex) {
		vertices.add(vertex);
		while(maxSize > 0 && vertices.size() > maxSize) {
			vertices.removeFirst();
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
		ColorRGBA[] arrcolors = new ColorRGBA[vertices.size()];
		
		for (int i = 0; i < arrcolors.length; i++) {
			arrcolors[i] = colorController.getColor(i);
		}
		
		System.out.println(Arrays.toString(arrcolors));
		setBuffer(Type.Color, 4, BufferUtils.createFloatBuffer(arrcolors));
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public LinkedList<Vector3f> getVertices() {
		return vertices;
	}

	public void setVertices(LinkedList<Vector3f> vertices) {
		this.vertices = vertices;
	}
}
