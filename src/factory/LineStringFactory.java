package nl.mistercavespider.lina.factory;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import nl.mistercavespider.lina.LineString;

/**
 * 
 * 
 * @author MisterCavespider
 */
public class LineStringFactory {
    
    protected static SimpleApplication app;
    
    public static void init(SimpleApplication app) {
        LineStringFactory.app = app;
    } 
    
    private static boolean checkInit() {
        return app != null;
    }
    
    protected static Material getStandardMat() {
        return new Material(app.getAssetManager(), "Standard LineStringFactory material");
    }
    
    /**
     * Builds a quadriletical using LineStrings.
     * 
     * Imagine a square. Now name all vertices A to D
     * starting left bottom going counter-clockwise.
     * D---C
     * | . |
     * A---B
     * The dot is the middle. The relative coordinates of
     * A to the dot are: (-length/2, -width/2).
     * 
     * @param width     The total distance between 2 points along the x-axis.
     * @param length    The total distance between 2 points along the z-axis.
     * @return
     */
    public static LineString buildQuad(float width, float length) {
        if(checkInit()) return null;
        
        //Create an Array containing Vector3fs
        Vector3f[] vectors = new Vector3f[4];
        vectors[0] = new Vector3f(-length/2, 0, -width/2);  //A
        vectors[1] = new Vector3f(-length/2, 0, width/2);  //B
        vectors[2] = new Vector3f(length/2, 0, width/2);  //C
        vectors[3] = new Vector3f(length/2, 0, -width/2);  //D
        
        //Create a LineString and add the Array
        LineString ls = new LineString(getStandardMat());
        ls.addPoint(vectors);
        
        return ls;
    }
    
}
