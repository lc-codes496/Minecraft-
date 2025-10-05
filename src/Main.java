package src;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

public class Main {
    private long window;

    public void run() {
        System.out.println("Iniciando Minecraft Java!");
        init();
        loop();

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        if (!GLFW.glfwInit())
            throw new IllegalStateException("Falha ao iniciar GLFW!");

        window = GLFW.glfwCreateWindow(800, 600, "Minecraft Java", 0, 0);
        if (window == 0)
            throw new RuntimeException("Falha ao criar janela!");

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }

    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glClearColor(0.3f, 0.6f, 1.0f, 0.0f); // céu azul

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
