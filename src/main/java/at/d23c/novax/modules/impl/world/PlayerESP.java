package at.d23c.novax.modules.impl.world;
import at.d23c.novax.events.ClientTickEvent;
import at.d23c.novax.events.RenderEntityEvent;
import at.d23c.novax.mixins.accessor.RenderManagerAccessMixin;
import at.d23c.novax.modules.Module;
import at.d23c.novax.modules.ModuleCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.weavemc.api.event.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerESP extends Module {

    public ArrayList<UUID> renderForUuids = new ArrayList<>();

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
    public void onClientTick(ClientTickEvent event){
        Minecraft.getMinecraft().theWorld.getLoadedEntityList().forEach((e) -> {
            if(e instanceof EntityPlayer){
                if(!renderForUuids.contains(e.getUniqueID())){
                    renderForUuids.add(e.getUniqueID());
                }
            }
        });
    }


    @SubscribeEvent
    public void onRenderWorldLast(RenderEntityEvent event) {
        if(renderForUuids.contains(event.entity.getUniqueID())){
            RenderManagerAccessMixin rm = ((RenderManagerAccessMixin) Minecraft.getMinecraft().getRenderManager());

            Minecraft mc = Minecraft.getMinecraft();
            if (mc.theWorld == null || mc.getRenderManager() == null || rm.renderPosX() == 0 || rm.renderPosY() == 0 || rm.renderPosZ() == 0)
                return;

            System.out.println("Tick Render Player event.entity");


            if (event.entity instanceof EntityPlayer) {
                double x = event.entity.lastTickPosX + (event.entity.posX - event.entity.lastTickPosX) * (double) event.tick - rm.renderPosX();
                double y = event.entity.lastTickPosY + (event.entity.posY - event.entity.lastTickPosY) * (double) event.tick - rm.renderPosY();
                double z = event.entity.lastTickPosZ + (event.entity.posZ - event.entity.lastTickPosZ) * (double) event.tick - rm.renderPosZ();
                float d = (float) 0 / 40.0F; // Expand


                GlStateManager.pushMatrix();
                if (true) {
                    GL11.glTranslated(x, y - 0.2D, z);
                    GL11.glRotated(-mc.getRenderManager().playerViewY, 0.0D, 1.0D, 0.0D);
                    GlStateManager.disableDepth();
                    GL11.glScalef(0.03F + d, 0.03F + d, 0.03F + d);
                    int outline = rainbowDraw(2L,1L);
                    float lineWidth = 1;


                    mc.getRenderManager().doRenderEntity(event.entity,event.x, event.y,event.z,event.yaw,event.tick,true);



                    drawLine(-20, 0, 0, -20, 75, 0, outline, lineWidth); // UL - OL
                    drawLine(20, 0, 0, 20, 75, 0, outline, lineWidth); // UR - OR

                    drawLine(-20, 0, 0, 20, 0, 0, outline, lineWidth); // UL - UR
                    drawLine(-20, 75, 0, 20, 75, 0, outline, lineWidth); // OL - OR


                    GlStateManager.enableDepth();
                }
                GlStateManager.popMatrix();
            }
        }

    }



    public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, int color, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        // Save the current color settings
        GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
        // Set the line color
        GL11.glColor4ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF), (byte) (color >> 24 & 0xFF));

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(x1, y1, z1);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glEnd();

        // Restore the previous color settings
        GL11.glPopAttrib();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glPopMatrix();
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
