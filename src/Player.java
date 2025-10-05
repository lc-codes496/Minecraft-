package src;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Player {
    private Vector3f position;
    private float yaw, pitch;
    private float speed = 5f;
    private float sensitivity = 0.1f;
    private float gravity = -9.8f;
    private float velocityY = 0;

    private Input input;

    public Player() {
        position = new Vector3f(25, 10, 25);
    }

    public void setInput(Input input) { this.input = input; }

    public void update(double deltaTime) {
        if (input == null) return;

        yaw += input.getDeltaX() * sensitivity;
        pitch -= input.getDeltaY() * sensitivity;
        if (pitch > 89) pitch = 89;
        if (pitch < -89) pitch = -89;

        Vector3f forward = new Vector3f((float)Math.sin(Math.toRadians(yaw)), 0, (float)-Math.cos(Math.toRadians(yaw))).normalize();
        Vector3f right = new Vector3f((float)Math.sin(Math.toRadians(yaw+90)), 0, (float)-Math.cos(Math.toRadians(yaw+90))).normalize();

        if (input.isKeyDown(GLFW.GLFW_KEY_W)) position.add(forward.x*speed*(float)deltaTime, 0, forward.z*speed*(float)deltaTime);
        if (input.isKeyDown(GLFW.GLFW_KEY_S)) position.sub(forward.x*speed*(float)deltaTime, 0, forward.z*speed*(float)deltaTime);
        if (input.isKeyDown(GLFW.GLFW_KEY_A)) position.sub(right.x*speed*(float)deltaTime, 0, right.z*speed*(float)deltaTime);
        if (input.isKeyDown(GLFW.GLFW_KEY_D)) position.add(right.x*speed*(float)deltaTime, 0, right.z*speed*(float)deltaTime);

        velocityY += gravity * (float)deltaTime;
        position.y += velocityY * (float)deltaTime;

        if (position.y < 1) { position.y = 1; velocityY = 0; }
        if (input.isKeyDown(GLFW.GLFW_KEY_SPACE) && position.y <= 1.01f) velocityY = 5f;
    }

    public Vector3f getPosition() { return position; }
    public float getYaw() { return yaw; }
    public float getPitch() { return pitch; }
            }
