package net.mcreator.end_elemetn.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.end_elemetn.entity.BulletentityEntity;
import net.mcreator.end_elemetn.client.model.Modelbullet;

public class BulletentityRenderer extends MobRenderer<BulletentityEntity, Modelbullet<BulletentityEntity>> {
	public BulletentityRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbullet<BulletentityEntity>(context.bakeLayer(Modelbullet.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(BulletentityEntity entity) {
		return ResourceLocation.parse("end_elemetn:textures/entities/" + entity.getTexture() + ".png");
	}
}