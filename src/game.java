package src;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Game {
    private World world;
    private Player player;
    private Renderer renderer;

    private boolean running = true;
    private double lastTime;

    public Game() {
        world = new World();
        player = new Player();
        renderer = new Renderer();
    }

    public void update() {
        double currentTime = GLFW.glfwGetTime();
        double deltaTime = currentTime - lastTime;
        lastTime = currentTime;

        // Atualiza jogador (movimento, física, etc)
        player.update(deltaTime);

        // Atualiza mundo (se precisar)
        world.update();

        // Renderiza tudo
        render();
    }

    private void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        renderer.renderWorld(world, player);
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
