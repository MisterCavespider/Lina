/**
 * LICENSE
 * 
 * *ahem*
 * One can do whatever they want with this code,
 * aslong as they meet the following terms:
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A line string - multiple lines.
 * 
 * 
 * @author MisterCavespider
 */
public class LineString {
    
    private HashMap<Integer,Vector3f> waypoints;
    
    private boolean connection;
    private Node lineNode;
    
    private final Material mat;
    
    public LineString(Material mat) {
        this.mat = mat;
        lineNode = new Node();
        waypoints = new HashMap<Integer, Vector3f>();
    }
    
    /**
     * Add a certain point.
     *
     * @param loc
     * @param point
     */
    public void addPoint(int loc, Vector3f point) {
        waypoints.put(loc, point);
        try {
            update();
        } catch (UnavailiblePointException ex) {
            //Nothing yet
        }
    }
    
    /**
     * Removes a certain point.
     * 
     * @param loc
     * @param point
     */
    public void remPoint(int loc, Vector3f point) {
        waypoints.put(loc, point);
        try {
            update();
        } catch (UnavailiblePointException ex) {
            //Nothing yet
        }
    }
    
    /**
     * Don't call it regularly.
     * 
     * It will be internally called.
     * 
     * @throws nl.mistercavespider.lina.UnavailiblePointException
     */
    protected void update() throws UnavailiblePointException {
        int total = waypoints.size();
        int counter = 0;
        while(counter < total) {
            Vector3f p1 = waypoints.get(counter);
            Vector3f p2 = waypoints.get(counter + 1);
            //Is it the last one? Should we connect?
            if(counter == total -1 && connection && p2 == null){
                p2 = waypoints.get(0);
            } else if(p2 == null) {
                //Is there nothing in this number?
                //Throw an exception
                throw new UnavailiblePointException();
            }
            
            //Creates the lines
            Line l = new Line(p1, p2);
            Geometry g = new Geometry("Geometry in a LineString: " + counter, l);
            g.setMaterial(mat);
            
            //In case 
            
            //Attaches the lines to a node
            lineNode.attachChild(g);
            
            //Add the counter
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
    
    public Node getAttach() {
        return lineNode;
    }
    
    
    
    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public Node getLineNode() {
        return lineNode;
    }

    public void setLineNode(Node lineNode) {
        this.lineNode = lineNode;
    }
    
}
