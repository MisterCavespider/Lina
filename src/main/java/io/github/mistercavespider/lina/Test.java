package io.github.mistercavespider.lina;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

import io.github.mistercavespider.lina.color.TransparentColorControl;
import io.github.mistercavespider.lina.ctrl.TimeTracer;

/**
 * Test temporary class.
 * 
 * DO NOT USE
 * 
 * @author MisterCavespider
 *
 */
public class Test extends SimpleApplication {

	public static void main(String[] args) {
		Test t = new Test();
		AppSettings sets = new AppSettings(true);
		sets.setFrameRate(60);
		t.setSettings(sets);
		t.start();
	}

	private float time;
	private long last = 0;
	private LineArray arr;
	private LineString str;
	private Geometry lineString;
	private Geometry g;
	
	@Override
	public void simpleInitApp() {
		flyCam.setMoveSpeed(45f);
		
		stateManager.attach(new ScreenshotAppState("image/"));
		
//		
//		arr = new LineArray(8);
//		Geometry geom = new Geometry("LineArray", arr);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTransparent(true);
		mat.setBoolean("VertexColor", true);
//		geom.setMaterial(mat);
//		rootNode.attachChild(geom);
//		
//		arr.setVertex(0, new Vector3f(0, 0, 0));
//		arr.setVertex(1, new Vector3f(1, 1, 1));
//		arr.setVertex(2, new Vector3f(2, 6, 4));
//		arr.setVertex(3, new Vector3f(3, 12, 9));
//		
//		arr.setVertex(4, new Vector3f(4, 20, 16));
//		arr.setVertex(5, new Vector3f(5, 30, 25));
//		arr.setVertex(6, new Vector3f(6, 42, 36));
//		arr.setVertex(7, new Vector3f(7, 56, 49));
//		
//		System.out.println();
//		
//		str = new LineString(64);
//		lineString = new Geometry("LineString", str);
//		lineString.setMaterial(mat.clone());
//		rootNode.attachChild(lineString);
//		
//		str.addPoint(Vector3f.ZERO, ColorRGBA.Red);
//		str.addPoint(Vector3f.UNIT_X, ColorRGBA.Red);
//		str.addPoint(Vector3f.UNIT_XYZ, ColorRGBA.Red);
		
		g = new Geometry("g", new Sphere(8, 8, .5f));
		g.setMaterial(mat.clone());
		rootNode.attachChild(g);
		
		TransparentColorControl tcc = new TransparentColorControl();
		tcc.setBaseColor(ColorRGBA.Green);
		
		g.addControl(new TimeTracer(mat.clone(), 10, 64, tcc));
		
	}

	@Override
	public void simpleUpdate(float tpf) {
		time += tpf;
//		
//		if(System.currentTimeMillis() - last > 50) {
//			last = System.currentTimeMillis();
//			str.addPoint(new Vector3f(time, time*time, 1), ColorRGBA.Red);
//			lineString.updateModelBound();
//		}
		
		g.setLocalTranslation( FastMath.cos( time*10 ), g.getLocalTranslation().y + tpf, FastMath.sin( time*10 ) );
	}
}
