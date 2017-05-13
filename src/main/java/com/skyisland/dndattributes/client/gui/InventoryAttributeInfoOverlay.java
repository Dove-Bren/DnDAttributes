package com.skyisland.dndattributes.client.gui;

import com.skyisland.dndattributes.DnDAttributesMod;
import com.skyisland.dndattributes.capability.IDnDAttributes;
import com.skyisland.dndattributes.config.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class InventoryAttributeInfoOverlay implements IOverlay {

	private static final ResourceLocation BACK_TEXT = new ResourceLocation(DnDAttributesMod.MODID, "textures/gui/overview_back.png");
	private static final ResourceLocation BACK_TITLE = new ResourceLocation(DnDAttributesMod.MODID, "textures/gui/overview_title.png");
	
	private static final int TEXT_HEIGHT = 215;
	private static final int TEXT_WIDTH = 182;
	private static final int GUI_HEIGHT = 200;
	private static final int GUI_WIDTH = 125;
	private static final int GUI_RIGHT_OFFSET = GUI_WIDTH + 5;
	private static final int GUI_TOP_OFFSET = 5;
	private static final int GUI_INNER_XOFFSET = 15;
	private static final int GUI_INNER_YOFFSET = 20;
	private static final int GUI_TITLE_HEIGHT = 15;
	
	private static final String PREFIX_UNLOCAL = "text.overview_gui";
	private static final String DEF_NO_ATTR = "text.overview_gui.attrerror";
	
	private EntityPlayer player;
	
	public InventoryAttributeInfoOverlay() {
		//this.player = player; //player not usually alive in time
	}
	
	@Override
	public void drawOverlay(int mouseX, int mouseY) {
		
		if (player == null) {
			player = Minecraft.getMinecraft().thePlayer;
		}
		
		//if still null, there's a problem
		if (player == null)
			return;

		if (!ModConfig.config.showInvInfo()) {
			return;
		}
		
		Minecraft mc = Minecraft.getMinecraft();

		if (mc.currentScreen == null || !(mc.currentScreen instanceof GuiInventory))
			return;
		
		final ScaledResolution scaledresolution = new ScaledResolution(mc);
		int screenWidth = scaledresolution.getScaledWidth();
		
		
		GlStateManager.color(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(BACK_TEXT);
		
		Gui.drawModalRectWithCustomSizedTexture(
				screenWidth - GUI_RIGHT_OFFSET, GUI_TOP_OFFSET, 0, 0,
				GUI_WIDTH, GUI_HEIGHT, GUI_WIDTH, GUI_HEIGHT);
		

		GlStateManager.color(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(BACK_TITLE);
		
		Gui.drawModalRectWithCustomSizedTexture(
				screenWidth - GUI_RIGHT_OFFSET, GUI_TOP_OFFSET + GUI_INNER_YOFFSET, 0, 0,
				GUI_WIDTH, GUI_TITLE_HEIGHT, GUI_WIDTH, GUI_TITLE_HEIGHT);
		
		
		drawAttributes((screenWidth - GUI_RIGHT_OFFSET) + GUI_INNER_XOFFSET,
				screenWidth - (5 + GUI_INNER_XOFFSET),
				GUI_TOP_OFFSET + GUI_INNER_YOFFSET + GUI_TITLE_HEIGHT + GUI_INNER_YOFFSET, //two yoffset to space title out
				DnDAttributesMod.instance.getAttributes(player));
		
	}
	
	private void drawAttributes(int leftX, int rightX, int topY, IDnDAttributes attr) {
		if (attr == null) {
			Minecraft.getMinecraft().fontRendererObj.drawString(I18n.format(DEF_NO_ATTR), leftX, topY, 0x00, false);
			return;
		}
		
		int y = 0;
		FontRenderer render = Minecraft.getMinecraft().fontRendererObj;
		String val;
		int valColor = ModConfig.config.showInvInfoColor();
		
		val = "" + attr.getStrength();
		render.drawString(I18n.format(PREFIX_UNLOCAL + ".strength"), leftX, topY + y, 0x00, false);
		render.drawString(val, rightX - (render.getStringWidth(val)), topY + y, valColor);
		y += 5 + render.FONT_HEIGHT;
		
		val = "" + attr.getConstitution();
		render.drawString(I18n.format(PREFIX_UNLOCAL + ".constitution"), leftX, topY + y, 0x00, false);
		render.drawString(val, rightX - (render.getStringWidth(val)), topY + y, valColor);
		y += 5 + render.FONT_HEIGHT;
		
		val = "" + attr.getDexterity();
		render.drawString(I18n.format(PREFIX_UNLOCAL + ".dexterity"), leftX, topY + y, 0x00, false);
		render.drawString(val, rightX - (render.getStringWidth(val)), topY + y, valColor);
		y += 5 + render.FONT_HEIGHT;
		
		val = "" + attr.getIntelligence();
		render.drawString(I18n.format(PREFIX_UNLOCAL + ".intelligence"), leftX, topY + y, 0x00, false);
		render.drawString(val, rightX - (render.getStringWidth(val)), topY + y, valColor);
		y += 5 + render.FONT_HEIGHT;
		
		val = "" + attr.getWisdom();
		render.drawString(I18n.format(PREFIX_UNLOCAL + ".wisdom"), leftX, topY + y, 0x00, false);
		render.drawString(val, rightX - (render.getStringWidth(val)), topY + y, valColor);
		y += 5 + render.FONT_HEIGHT;
		
		val = "" + attr.getCharisma();
		render.drawString(I18n.format(PREFIX_UNLOCAL + ".charisma"), leftX, topY + y, 0x00, false);
		render.drawString(val, rightX - (render.getStringWidth(val)), topY + y, valColor);
		y += 5 + render.FONT_HEIGHT;
		
		
	}
}
