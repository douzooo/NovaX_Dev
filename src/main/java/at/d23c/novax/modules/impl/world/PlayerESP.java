package at.d23c.novax.modules.impl.world;

import at.d23c.novax.events.RenderEntityEvent;
import at.d23c.novax.mixins.accessor.RenderManagerAccessMixin;
import at.d23c.novax.modules.Module;
import at.d23c.novax.modules.ModuleCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import net.weavemc.api.event.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class PlayerESP extends Module {
    public PlayerESP(String moduleName, ModuleCategory moduleCategory) {
        super(moduleName, moduleCategory);
    }

    @Override
    public void onEnable() {
        System.out.println("Enabled Player ESP");
    }

    @Override
    public void onDisable() {
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderEntityEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null || mc.getRenderManager() == null || ((RenderManagerAccessMixin)mc.getRenderManager()).renderPosX() == 0 || ((RenderManagerAccessMixin)mc.getRenderManager()).renderPosY() == 0 || ((RenderManagerAccessMixin)mc.getRenderManager()).renderPosZ() == 0)
            return;

        double x = event.entity.lastTickPosX + (event.entity.posX - event.entity.lastTickPosX) * (double) event.tick - ((RenderManagerAccessMixin)mc.getRenderManager()).renderPosX();
        double y = event.entity.lastTickPosY + (event.entity.posY - event.entity.lastTickPosY) * (double) event.tick - ((RenderManagerAccessMixin)mc.getRenderManager()).renderPosY();
        double z = event.entity.lastTickPosZ + (event.entity.posZ - event.entity.lastTickPosZ) * (double) event.tick - ((RenderManagerAccessMixin)mc.getRenderManager()).renderPosZ();

        GlStateManager.pushMatrix();

        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GL11.glTranslated(x, y, z);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F); // R,G,B,Alpha
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldRenderer.pos(-0.5, -0.5, 0.0).endVertex(); // Bottom-left
        worldRenderer.pos(0.5, -0.5, 0.0).endVertex();  // Bottom-right
        worldRenderer.pos(0.5, 0.5, 0.0).endVertex();   // Top-right
        worldRenderer.pos(-0.5, 0.5, 0.0).endVertex();  // Top-left
        tessellator.draw();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }


    public static int rainbowDraw(long speed, long... delay) {
        long time = System.currentTimeMillis() + (delay.length > 0 ? delay[0] : 0L);
        return Color.getHSBColor((float) (time % (15000L / speed)) / (15000.0F / (float) speed), 1.0F, 1.0F)
                .getRGB();
    }

    public static void dGR(int left, int top, int right, int bottom, int startColor, int endColor) {
        int j;
        if (left < right) {
            j = left;
            left = right;
            right = j;
        }

        if (top < bottom) {
            j = top;
            top = bottom;
            bottom = j;
        }

        float f = (float) ((startColor >> 24) & 255) / 255.0F;
        float f1 = (float) ((startColor >> 16) & 255) / 255.0F;
        float f2 = (float) ((startColor >> 8) & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) ((endColor >> 24) & 255) / 255.0F;
        float f5 = (float) ((endColor >> 16) & 255) / 255.0F;
        float f6 = (float) ((endColor >> 8) & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, 0.0D).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, 0.0D).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, 0.0D).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }


}
