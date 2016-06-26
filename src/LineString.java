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
package nl.mistercavespider.lina;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;
import java.util.ArrayList;

/**
 * A line string - multiple lines.
 * 
 * 
 * @author MisterCavespider
 */
public class LineString extends Node {
    
    private final ArrayList<Vector3f> waypoints;
    
    private boolean connection;
    
    private final Material mat;
    
    public LineString(Material mat) {
        this.mat = mat;
        waypoints = new ArrayList<Vector3f>();
    }
    
    /**
     * Adds the given point.
     *
     * @param point
     */
    public void addPoint(Vector3f point) {
        waypoints.add(point);
        try {
            update();
        } catch (UnavailiblePointException ex) {
            //Nothing yet
        }
    }
    
    /**
     * Adds the given points.
     * Loops through the Array and calls the
     * other .addCall(Vector3f point).
     * 
     * @param points
     */
    public void addPoint(Vector3f[] points) {
        for (Vector3f point : points) {
            addPoint(point);
        }
    }
    
    /**
     * Removes the given point.
     * 
     * @param point
     */
    public void remPoint(Vector3f point) {
        waypoints.remove(point);
        try {
            update();
        } catch (UnavailiblePointException ex) {
            //Nothing yet
        }
    }
    
    /**
     * Don't call it regularly.
     * 
     * It will be internally called when needed.
     * 
     * @throws nl.mistercavespider.lina.UnavailiblePointException
     */
    protected void update() throws UnavailiblePointException {
        Vector3f[] points = (Vector3f[]) waypoints.toArray();
        
        int total = points.length;
        int counter = 0;
        while(counter<=total) {
            //Get the starting point
            Vector3f start = points[counter];
            Vector3f end = points[counter + 1];
            
            //Draw a line
            Line l = new Line(start, end);
            Geometry geom = new Geometry("noname, in a LineString: { "+ counter +" }", l);
            
            //Attach the line to this, a Node
            attachChild(geom);
            
            counter++;
        }
    }
    
    public void forceUpdate() {
        try {
            update();
        } catch (UnavailiblePointException ex) {
            //Nothing
        }
    }
    
    
    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        String out = "(LineString) { " + "connection: " + connection + ", \n" +
                    "waypoints: " + waypoints;
        return out;
    }
    
    
}
