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

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Test extends SimpleApplication {

	public static void main(String[] args) {
		Test t = new Test();
		t.start();
	}

	private float time;
	private long last = 0;
	private LineArray arr;
	private LineString str;
	private Geometry lineString;
	
	@Override
	public void simpleInitApp() {
		flyCam.setMoveSpeed(45f);
		
		arr = new LineArray(8);
		Geometry geom = new Geometry("LineArray", arr);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setBoolean("VertexColor", true);
		geom.setMaterial(mat);
		//rootNode.attachChild(geom);
		
		arr.setVertex(0, new Vector3f(0, 0, 0));
		arr.setVertex(1, new Vector3f(1, 1, 1));
		arr.setVertex(2, new Vector3f(2, 6, 4));
		arr.setVertex(3, new Vector3f(3, 12, 9));
		
		arr.setVertex(4, new Vector3f(4, 20, 16));
		arr.setVertex(5, new Vector3f(5, 30, 25));
		arr.setVertex(6, new Vector3f(6, 42, 36));
		arr.setVertex(7, new Vector3f(7, 56, 49));
		
		System.out.println();
		
		str = new LineString(64);
		lineString = new Geometry("LineString", str);
		lineString.setMaterial(mat.clone());
		rootNode.attachChild(lineString);
		
		str.addPoint(Vector3f.ZERO, ColorRGBA.Red);
		str.addPoint(Vector3f.UNIT_X, ColorRGBA.Red);
		str.addPoint(Vector3f.UNIT_XYZ, ColorRGBA.Red);
	}

	@Override
	public void simpleUpdate(float tpf) {
		time += tpf;
		
		if(System.currentTimeMillis() - last > 50) {
			last = System.currentTimeMillis();
			str.addPoint(new Vector3f(time, time*time, 1), ColorRGBA.Red);
			lineString.updateModelBound();
		}
	}
}
