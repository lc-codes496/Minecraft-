package src;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class Renderer {

    public Renderer() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glClearColor(0.3f, 0.6f, 1.0f, 0f);
    }

    public void renderWorld(World world, Player player) {
        setupCamera(player);
        for (Block block : world.getBlocks()) renderBlock(block);
    }

    private void setupCamera(Player player) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        float aspect = 800f/600f;
        float fov = 70f;
        float near = 0.1f;
        float far = 100f;
        float y_scale = (float)(1f/Math.tan(Math.toRadians(fov/2f)));
        float x_scale = y_scale / aspect;
        GL11.glFrustum(-x_scale, x_scale, -y_scale, y_scale, near, far);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glRotatef(player.getPitch(),1,0,0);
        GL11.glRotatef(player.getYaw(),0,1,0);
        GL11.glTranslatef(-player.getPosition().x, -player.getPosition().y, -player.getPosition().z);
    }

    public void renderBlock(Block block) {
        Vector3f pos = block.getPosition();
        int texID = block.getTextureID();

        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x,pos.y,pos.z);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);

        GL11.glBegin(GL11.GL_QUADS);
        // Frente
        GL11.glTexCoord2f(0,0); GL11.glVertex3f(0,0,0);
        GL11.glTexCoord2f(1,0); GL11.glVertex3f(1,0,0);
        GL11.glTexCoord2f(1,1); GL11.glVertex3f(1,1,0);
        GL11.glTexCoord2f(0,1); GL11.glVertex3f(0,1,0);
        // Trás
        GL11.glTexCoord2f(0,0); GL11.glVertex3f(0,0,1);
        GL11.glTexCoord2f(1,0); GL11.glVertex3f(1,0,1);
        GL11.glTexCoord2f(1,1); GL11.glVertex3f(1,1,1);
        GL11.glTexCoord2f(0,1); GL11.glVertex3f(0,1,1);
        // Lados
        GL11.glTexCoord2f(0,0); GL11.glVertex3f(0,0,0); GL11.glTexCoord2f(1,0); GL11.glVertex3f(0,1,0);
        GL11.glTexCoord2f(1,1); GL11.glVertex3f(0,1,1); GL11.glTexCoord2f(0,1); GL11.glVertex3f(0,0,1);
        GL11.glTexCoord2f(0,0); GL11.glVertex3f(1,0,0); GL11.glTexCoord2f(1,0); GL11.glVertex3f(1,1,0);
        GL11.glTexCoord2f(1,1); GL11.glVertex3f(1,1,1); GL11.glTexCoord2f(0,1); GL11.glVertex3f(1,0,1);
        // Topo
        GL11.glTexCoord2f(0,0); GL11.glVertex3f(0,1,0); GL11.glTexCoord2f(1,0); GL11.glVertex3f(1,1,0);
        GL11.glTexCoord2f(1,1); GL11.glVertex3f(1,1,1); GL11.glTexCoord2f(0,1); GL11.glVertex3f(0,1,1);
        // Base
        GL11.glTexCoord2f(0,0); GL11.glVertex3f(0,0,0); GL11.glTexCoord2f(1,0); GL11.glVertex3f(1,0,0);
        GL11.glTexCoord2f(1,1); GL11.glVertex3f(1,0,1); GL11.glTexCoord2f(0,1); GL11.glVertex3f(0,0,1);
        GL11.glEnd();

        GL11.glPopMatrix();
    }
            }
