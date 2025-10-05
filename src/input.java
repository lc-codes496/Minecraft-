package src;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Input {

    private long window;

    private boolean[] keys = new boolean[1024];
    private double mouseX, mouseY;
    private double deltaX, deltaY;
    private boolean firstMouse = true;

    public Input(long window) {
        this.window = window;

        // Callback de teclado
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long win, int key, int scancode, int action, int mods) {
                if (key < 0 || key >= keys.length) return;
                keys[key] = action != GLFW.GLFW_RELEASE;
            }
        });

        // Callback do mouse
        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long win, double xpos, double ypos) {
                if (firstMouse) {
                    mouseX = xpos;
                    mouseY = ypos;
                    firstMouse = false;
                }
                deltaX = xpos - mouseX;
                deltaY = ypos - mouseY;
                mouseX = xpos;
                mouseY = ypos;
            }
        });

        // Captura do mouse
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    }

    public boolean isKeyDown(int key) {
        if (key < 0 || key >= keys.length) return false;
        return keys[key];
    }

    public double getDeltaX() {
        double dx = deltaX;
        deltaX = 0;
        return dx;
    }

    public double getDeltaY() {
        double dy = deltaY;
        deltaY = 0;
        return dy;
    }
                              }
