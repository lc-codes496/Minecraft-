package src;

import org.joml.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private List<Block> blocks;
    private int width = 50;
    private int depth = 50;

    public World() {
        blocks = new ArrayList<>();
        generateTerrain();
    }

    private void generateTerrain() {
        Random random = new Random();

        for (int x = 0; x < width; x++) {
            for (int z = 0; z < depth; z++) {
                int height = 3 + random.nextInt(3); // altura do bloco
                for (int y = 0; y < height; y++) {
                    BlockType type;
                    if (y == height - 1) type = BlockType.GRASS;
                    else type = BlockType.DIRT;

                    int textureID = getTextureIDForBlock(type); // atribui a textura automaticamente
                    blocks.add(new Block(new Vector3f(x, y, z), type, textureID));
                }

                // Adiciona pedra abaixo da terra
                blocks.add(new Block(new Vector3f(x, -1, z), BlockType.STONE, getTextureIDForBlock(BlockType.STONE)));
            }
        }
    }

    private int getTextureIDForBlock(BlockType type) {
        // Aqui você pode usar TextureLoader.loadTexture("assets/textures/...") 
        // ou deixar um ID padrão, por exemplo:
        switch (type) {
            case GRASS: return 1;
            case DIRT: return 2;
            case STONE: return 3;
            case SAND: return 4;
            case WATER: return 5;
            default: return 1;
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void update() {
        // Futuras atualizações do mundo (árvores, mobs etc.)
    }
        }
