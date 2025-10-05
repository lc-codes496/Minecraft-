package src;

import org.lwjgl.opengl.GL11;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Renderer {

    public Renderer() {
        // Configurações básicas de OpenGL
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0.3f, 0.6f, 1.0f, 0.0f); // céu azul
    }

    public void renderWorld(World world, Player player) {
        // Aqui podemos adicionar a projeção e view da câmera
        setupCamera(player);

        // Renderizar blocos do mundo
        for (Block block : world.getBlocks()) {
            renderBlock(block);
        }
    }

    private void setupCamera(Player player) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        // Perspectiva básica
        float aspect = 800f / 600f;
        float fov = 70f;
        float near = 0.1f;
        float far = 100f;
        float y_scale = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));
        float x_scale = y_scale / aspect;
        float frustum_length = far - near;

        // Usando matriz OpenGL legacy para simplificar
        GL11.glFrustum(-x_scale, x_scale, -y_scale, y_scale, near, far);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glRotatef(player.getPitch(), 1, 0, 0);
        GL11.glRotatef(player.getYaw(), 0, 1, 0);
        GL11.glTranslatef(-player.getPosition().x, -player.getPosition().y, -player.getPosition().z);
    }

    private void renderBlock(Block block) {
        Vector3f pos = block.getPosition();

        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x, pos.y, pos.z);

        GL11.glBegin(GL11.GL_QUADS);

        // Cubo simples com cores diferentes para cada face
        // Frente
        GL11.glColor3f(1, 0, 0); // vermelho
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(1, 0, 0);
        GL11.glVertex3f(1, 1, 0);
        GL11.glVertex3f(0, 1, 0);

        // Trás
        GL11.glColor3f(0, 1, 0); // verde
        GL11.glVertex3f(0, 0, 1);
        GL11.glVertex3f(1, 0, 1);
        GL11.glVertex3f(1, 1, 1);
        GL11.glVertex3f(0, 1, 1);

        // Lados
        GL11.glColor3f(0, 0, 1); // azul
        GL11.glVertex3f(0,0,0); GL11.glVertex3f(0,1,0); GL11.glVertex3f(0,1,1); GL11.glVertex3f(0,0,1); // esquerda
        GL11.glVertex3f(1,0,0); GL11.glVertex3f(1,1,0); GL11.glVertex3f(1,1,1); GL11.glVertex3f(1,0,1); // direita
        GL11.glVertex3f(0,1,0); GL11.glVertex3f(1,1,0); GL11.glVertex3f(1,1,1); GL11.glVertex3f(0,1,1); // topo
        GL11.glVertex3f(0,0,0); GL11.glVertex3f(1,0,0); GL11.glVertex3f(1,0,1); GL11.glVertex3f(0,0,1); // base

        GL11.glEnd();
        GL11.glPopMatrix();
    }
                       }
