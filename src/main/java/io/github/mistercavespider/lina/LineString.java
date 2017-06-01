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
		while(vertices.size() > maxSize) {
			vertices.removeFirst();
		}
		
		colors.add(color);
		while(colors.size() > maxSize) {
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
