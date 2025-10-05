package src;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {
    private long window;
    private int width = 800;
    private int height = 600;

    private Game game;
    private Input input;

    public void run() {
        System.out.println("Iniciando Minecraft Java!");
        init();
        loop();

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Falha ao iniciar GLFW!");
        }

        window = GLFW.glfwCreateWindow(width, height, "Minecraft Java", 0, 0);
        if (window == 0) throw new RuntimeException("Falha ao criar janela!");

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);
        GL.createCapabilities();

        GL11.glEnable(GL11.GL_DEPTH_TEST);

        // Inicializa Input
        input = new Input(window);

        // Inicializa o Game e conecta o Input ao Player
        game = new Game();
        game.player.setInput(input);
    }

    private void loop() {
        double lastTime = GLFW.glfwGetTime();

        while (!GLFW.glfwWindowShouldClose(window)) {
            double currentTime = GLFW.glfwGetTime();
            double deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            // Atualiza o jogo
            game.update();

            // Troca buffers
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
