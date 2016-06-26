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

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import java.util.ArrayList;

/**
 *
 * @author MisterCavespider
 */
public class Lina extends Mesh {
    
    ArrayList<Vector3f> vectorList;
    
    Vector3f[] vectorArray;
    Vector3f[] texCoordArray;
    Vector3f[] indexArray;
    private Vector2f[] texCoord;
    int[] indexes = {0,1,2, 0,2,3};
    
    public Lina() {
        vectorList = new ArrayList<Vector3f>();
    }
    
    public Lina(Vector3f[] vectors) {
        vectorList = new ArrayList<Vector3f>();
    }
    
    public void update() {
        //Convert the list to the array
        Vector3f[] arr = vectorList.toArray(new Vector3f[vectorList.size()]);
        vectorArray = arr;
        
        //Texture Coordinates
        texCoord = new Vector2f[4];
        texCoord[0] = new Vector2f(0,0);
        texCoord[1] = new Vector2f(1,0);
        texCoord[2] = new Vector2f(0,1);
        texCoord[3] = new Vector2f(1,1);
        
        //Standard mesh stuff
        setMode(Mode.Lines);
        
        setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vectorArray));
        setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        updateBound();
    }
    
    
    
    public void addPoint(Vector3f v) {
        vectorList.add(v);
    }
    
    public void remPoint(Vector3f v) {
        vectorList.remove(v);
    }
}
