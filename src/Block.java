package src;

import org.joml.Vector3f;

public class Block {
    private Vector3f position;
    private BlockType type;

    public Block(Vector3f position, BlockType type) {
        this.position = position;
        this.type = type;
    }

    public Vector3f getPosition() {
        return position;
    }

    public BlockType getType() {
        return type;
    }
}
