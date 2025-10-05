package src;

public class Game {
    public Player player;
    public World world;
    private Renderer renderer;

    public Game() {
        world = new World();
        player = new Player();
        renderer = new Renderer();
    }

    public void update(double deltaTime) {
        player.update(deltaTime);
        world.update();
        renderer.renderWorld(world, player);
    }
}
