package net.mcreator.end_elemetn.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.end_elemetn.world.inventory.TradelingGUIMenu;
import net.mcreator.end_elemetn.network.TradelingGUIButtonMessage;
import net.mcreator.end_elemetn.init.EndElemetnModScreens;
import net.mcreator.end_elemetn.EndElemetnMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class TradelingGUIScreen extends AbstractContainerScreen<TradelingGUIMenu> implements EndElemetnModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_loot1;
	private ImageButton imagebutton_loot2;
	private ImageButton imagebutton_loot3;

	public TradelingGUIScreen(TradelingGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = new ResourceLocation("end_elemetn:textures/screens/tradeling_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/shopmark.png"), this.leftPos + -23, this.topPos + -36, 0, 0, 256, 96, 256, 96);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/arrow.png"), this.leftPos + 21, this.topPos + 29, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/arrow.png"), this.leftPos + 21, this.topPos + 46, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/arrow.png"), this.leftPos + 21, this.topPos + 63, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/coin1.png"), this.leftPos + 40, this.topPos + 29, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/coin2.png"), this.leftPos + 40, this.topPos + 45, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/coin3.png"), this.leftPos + 40, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(new ResourceLocation("end_elemetn:textures/screens/arrow.png"), this.leftPos + 109, this.topPos + 57, 0, 0, 16, 16, 16, 16);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		imagebutton_loot1 = new ImageButton(this.leftPos + 2, this.topPos + 29, 16, 16, 0, 0, 16, new ResourceLocation("end_elemetn:textures/screens/atlas/imagebutton_loot1.png"), 16, 32, e -> {
			int x = TradelingGUIScreen.this.x;
			int y = TradelingGUIScreen.this.y;
			if (true) {
				EndElemetnMod.PACKET_HANDLER.sendToServer(new TradelingGUIButtonMessage(0, x, y, z));
				TradelingGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_loot1);
		imagebutton_loot2 = new ImageButton(this.leftPos + 2, this.topPos + 46, 16, 16, 0, 0, 16, new ResourceLocation("end_elemetn:textures/screens/atlas/imagebutton_loot2.png"), 16, 32, e -> {
			int x = TradelingGUIScreen.this.x;
			int y = TradelingGUIScreen.this.y;
			if (true) {
				EndElemetnMod.PACKET_HANDLER.sendToServer(new TradelingGUIButtonMessage(1, x, y, z));
				TradelingGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_loot2);
		imagebutton_loot3 = new ImageButton(this.leftPos + 2, this.topPos + 63, 16, 16, 0, 0, 16, new ResourceLocation("end_elemetn:textures/screens/atlas/imagebutton_loot3.png"), 16, 32, e -> {
			int x = TradelingGUIScreen.this.x;
			int y = TradelingGUIScreen.this.y;
			if (true) {
				EndElemetnMod.PACKET_HANDLER.sendToServer(new TradelingGUIButtonMessage(2, x, y, z));
				TradelingGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_loot3);
	}
}