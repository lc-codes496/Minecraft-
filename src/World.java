package src;

import org.joml.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private List<Block> blocks;
    private int width = 50;
    private int depth = 50;

    // IDs das texturas
    private int grassTex, dirtTex, stoneTex, sandTex, waterTex;

    public World() {
        blocks = new ArrayList<>();
        loadTextures();
        generateTerrain();
    }

    private void loadTextures() {
        // Carrega as texturas automaticamente da pasta assets/textures
        grassTex = TextureLoader.loadTexture("assets/textures/grass.png");
        dirtTex = TextureLoader.loadTexture("assets/textures/dirt.png");
        stoneTex = TextureLoader.loadTexture("assets/textures/stone.png");
        sandTex = TextureLoader.loadTexture("assets/textures/sand.png");
        waterTex = TextureLoader.loadTexture("assets/textures/water.png");
    }

    private void generateTerrain() {
        Random random = new Random();

        for (int x = 0; x < width; x++) {
            for (int z = 0; z < depth; z++) {
                int height = 3 + random.nextInt(3); // altura do bloco

                for (int y = 0; y < height; y++) {
                    BlockType type = (y == height - 1) ? BlockType.GRASS : BlockType.DIRT;
                    int texID = getTextureIDForBlock(type);
                    blocks.add(new Block(new Vector3f(x, y, z), type, texID));
                }

                // Pedra abaixo da terra
                blocks.add(new Block(new Vector3f(x, -1, z), BlockType.STONE, stoneTex));
            }
        }
    }

    private int getTextureIDForBlock(BlockType type) {
        switch (type) {
            case GRASS: return grassTex;
            case DIRT:  return dirtTex;
            case STONE: return stoneTex;
            case SAND:  return sandTex;
            case WATER: return waterTex;
            default: return grassTex;
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void update() {
        // Futuras atualizações do mundo (árvores, mobs etc.)
    }
                                                  }
