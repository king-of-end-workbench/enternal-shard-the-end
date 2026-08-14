package net.mcreator.end_elemetn.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.mcreator.end_elemetn.block.entity.EndCityVaultBlockEntity;
import net.mcreator.end_elemetn.init.EndElemetnModItems;

/**
 * Mirrors vanilla's own VaultRenderer: the preview item is drawn directly here, as part of the
 * normal block entity render pass - never a real spawned entity. This is what actually fixes the
 * transparency/positioning/cleanup problems the old ItemDisplay-entity hack kept running into: there
 * is no separate entity to fight with the block's own faces or to leak when the block is broken.
 */
public class EndCityVaultRenderer implements BlockEntityRenderer<EndCityVaultBlockEntity> {
	// One representative item per loot tier - order MUST match the tier checks in
	// EndCityVaultPriShchielchkiePKMPoBlokuProcedure, since the block entity's stored tier index is
	// shared by both what's rendered here and what's actually given when the vault is opened.
	private static final ItemStack[] PREVIEW_ITEMS = { new ItemStack(Items.IRON_INGOT), new ItemStack(Items.DIAMOND), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), new ItemStack(Items.ELYTRA),
			new ItemStack(Items.DIAMOND_CHESTPLATE), new ItemStack(EndElemetnModItems.ENDERITE_LOOTBOX.get()) };

	private final ItemRenderer itemRenderer;

	public EndCityVaultRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.getItemRenderer();
	}

	@Override
	public void render(EndCityVaultBlockEntity be, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		if (be.getLevel() == null)
			return;
		int tier = Mth.clamp(be.getCurrentTier(), 0, PREVIEW_ITEMS.length - 1);
		ItemStack item = PREVIEW_ITEMS[tier];

		poseStack.pushPose();
		poseStack.translate(0.5F, 0.55F, 0.5F);
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, be.getPreviousSpin(), be.getSpin())));
		poseStack.scale(0.65F, 0.65F, 0.65F);
		this.itemRenderer.renderStatic(item, ItemDisplayContext.GROUND, packedLight, packedOverlay, poseStack, bufferSource, be.getLevel(), 0);
		poseStack.popPose();
	}
}
