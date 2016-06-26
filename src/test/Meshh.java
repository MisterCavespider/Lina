/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.mistercavespider.lina.test;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;

/**
 *
 * @author MisterCavespider
 */
public class Meshh extends Mesh {
    
    Vector3f[] vertices;
    Vector2f[] texCoord;
    int[] indexes = {0,1,2, 0,2,3};
    
    public Meshh() {
        initParam();
        
        setMode(Mode.Lines);
        setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        
    }
    
    protected void initParam() {
        vertices = new Vector3f[4];
        vertices[0] = new Vector3f(0, 0, 0);
        vertices[1] = new Vector3f(1, 0, 0);
        vertices[2] = new Vector3f(0, 2, 0);
        vertices[3] = new Vector3f(0, 0, 3);
        
        texCoord = new Vector2f[4];
        texCoord[0] = new Vector2f(0,0);
        texCoord[1] = new Vector2f(1,0);
        texCoord[2] = new Vector2f(0,1);
        texCoord[3] = new Vector2f(1,1);
    }
    
}
