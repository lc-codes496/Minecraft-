package src;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Player {
    private Vector3f position;
    private float yaw;   // rotação horizontal
    private float pitch; // rotação vertical
    private float speed = 5f;       // blocos por segundo
    private float sensitivity = 0.1f; // sensibilidade do mouse
    private float gravity = -9.8f;
    private float velocityY = 0;

    private Input input;

    public Player() {
        position = new Vector3f(25, 10, 25); // posição inicial
        yaw = 0;
        pitch = 0;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public void update(double deltaTime) {
        if (input == null) return;

        // Atualiza rotação da câmera pelo mouse
        yaw += input.getDeltaX() * sensitivity;
        pitch -= input.getDeltaY() * sensitivity;
        if (pitch > 89) pitch = 89;
        if (pitch < -89) pitch = -89;

        // Vetor de direção baseado na rotação
        Vector3f forward = new Vector3f();
        forward.x = (float) Math.sin(Math.toRadians(yaw));
        forward.y = 0;
        forward.z = (float) -Math.cos(Math.toRadians(yaw));
        forward.normalize();

        Vector3f right = new Vector3f();
        right.x = (float) Math.sin(Math.toRadians(yaw + 90));
        right.z = (float) -Math.cos(Math.toRadians(yaw + 90));
        right.normalize();

        // Movimento WASD
        if (input.isKeyDown(GLFW.GLFW_KEY_W)) position.add(forward.x * speed * (float)deltaTime, 0, forward.z * speed * (float)deltaTime);
        if (input.isKeyDown(GLFW.GLFW_KEY_S)) position.sub(forward.x * speed * (float)deltaTime, 0, forward.z * speed * (float)deltaTime);
        if (input.isKeyDown(GLFW.GLFW_KEY_A)) position.sub(right.x * speed * (float)deltaTime, 0, right.z * speed * (float)deltaTime);
        if (input.isKeyDown(GLFW.GLFW_KEY_D)) position.add(right.x * speed * (float)deltaTime, 0, right.z * speed * (float)deltaTime);

        // Gravidade simples
        velocityY += gravity * (float) deltaTime;
        position.y += velocityY * (float) deltaTime;

        // Colisão simples com o chão
        if (position.y < 1) {
            position.y = 1;
            velocityY = 0;
        }

        // Pulo
        if (input.isKeyDown(GLFW.GLFW_KEY_SPACE) && position.y <= 1.01f) {
            velocityY = 5f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
          }
