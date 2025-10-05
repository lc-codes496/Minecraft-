package src;

import org.joml.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private List<Block> blocks;
    private int width = 50;
    private int depth = 50;
    private int height = 10;

    public World() {
        blocks = new ArrayList<>();
        generateTerrain();
    }

    private void generateTerrain() {
        Random random = new Random();

        for (int x = 0; x < width; x++) {
            for (int z = 0; z < depth; z++) {
                // Altura base com ruído simples
                int y = 3 + random.nextInt(3); // altura entre 3 e 5
                for (int i = 0; i < y; i++) {
                    BlockType type = (i == y - 1) ? BlockType.GRASS : BlockType.DIRT;
                    blocks.add(new Block(new Vector3f(x, i, z), type));
                }
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void update() {
        // Futuras atualizações do mundo (árvores, mobs, etc)
    }
  }
