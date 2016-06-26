/**
 * LICENSE
 * 
 * *ahem*.
 * One can do whatever they want with this code,
 * as long as they meet the following terms:
 * 
 * 1. One must be able to prove at any time that they are alive.
 * 
 * That's it. Please don't give this to robots.
 */
package nl.mistercavespider.lina.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import nl.mistercavespider.lina.Lina;

/**
 *
 * @author MisterCavespider
 */
public class Main extends SimpleApplication {
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Meshh meshh = new Meshh();
        
        Geometry g = new Geometry("g", meshh);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        g.setMaterial(mat);
        rootNode.attachChild(g);
        
        Lina l = new Lina();
        l.addPoint(new Vector3f(0, 4, 4));
        l.addPoint(new Vector3f(4, 4, 4));
        l.addPoint(new Vector3f(0, 3, 0));
        l.addPoint(new Vector3f(2, 5, 1));
        l.update();
        Geometry g2 = new Geometry("g2", l);
        Material mat2 = mat;
        mat2.setColor("Color", ColorRGBA.Orange);
        g2.setMaterial(mat2);
        rootNode.attachChild(g2);
    }
    
}
