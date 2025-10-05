package src;

import org.joml.Vector3f;

public class Block {
    private Vector3f position;
    private BlockType type;
    private int textureID; // ID da textura OpenGL

    public Block(Vector3f position, BlockType type, int textureID) {
        this.position = position;
        this.type = type;
        this.textureID = textureID;
    }

    public Vector3f getPosition() {
        return position;
    }

    public BlockType getType() {
        return type;
    }

    public int getTextureID() {
        return textureID;
    }
}
