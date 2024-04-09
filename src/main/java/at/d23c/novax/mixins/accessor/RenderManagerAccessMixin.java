package at.d23c.novax.mixins.accessor;

import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderManager.class)
public interface RenderManagerAccessMixin {

	@Accessor("renderPosX")
	double renderPosX();

	@Accessor("renderPosY")
	double renderPosY();

	@Accessor("renderPosZ")
	double renderPosZ();
}
