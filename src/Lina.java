/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.mistercavespider.lina;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author MisterCavespider
 */
public class Lina extends Mesh {
    
    ArrayList<Vector3f> vectorList;
    
    Vector3f[] vectorArray;
    Vector3f[] texCoordArray;
    Vector3f[] indexArray;
    
    public Lina() {
        vectorList = new ArrayList<Vector3f>();
        update();
    }
    
    public Lina(Vector3f[] vectors) {
        vectorList = new ArrayList<Vector3f>();
        update();
    }
    
    private void update() {
        //Standard mesh stuff
        setMode(Mode.Lines);
        
        setBuffer(Type.Position, 3, getFloats(vectorArray));
        updateBound();
    }
    
    public void addPoint(Vector3f v) {
        vectorList.add(v);
    }
    
    public void remPoint(Vector3f v) {
        vectorList.remove(v);
    }
    
    private float[] getFloats(Vector3f[] vecs) {
        int length = 3*vecs.length;
        float[] floats = new float[length];
        
        int i = 0;
        int j = 0;
        while(i<vecs.length) {
            floats[i] = vecs[j].x;
            floats[i+1] = vecs[j].y;
            floats[i+2] = vecs[j].z;
            
            //Increase i by 3
            i = i+3;
            //Increase j by 1
            j++;
        }
        System.out.println(Arrays.toString(floats)); 
        
        return floats;
    } 
}
