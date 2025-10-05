public void renderBlock(Block block) {
    Vector3f pos = block.getPosition();
    int texID = block.getTextureID();

    GL11.glPushMatrix();
    GL11.glTranslatef(pos.x, pos.y, pos.z);

    GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);

    GL11.glBegin(GL11.GL_QUADS);
    GL11.glTexCoord2f(0, 0); GL11.glVertex3f(0, 0, 0);
    GL11.glTexCoord2f(1, 0); GL11.glVertex3f(1, 0, 0);
    GL11.glTexCoord2f(1, 1); GL11.glVertex3f(1, 1, 0);
    GL11.glTexCoord2f(0, 1); GL11.glVertex3f(0, 1, 0);
    GL11.glEnd();

    GL11.glPopMatrix();
}
