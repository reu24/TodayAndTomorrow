package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.AlloyFurnaceContainer;
import com.reu_24.tat.util.ModResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlloyFurnaceScreen extends EnergyScreen<AlloyFurnaceContainer> {
    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/alloy_furnace.png");

    public AlloyFurnaceScreen(AlloyFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        guiLeft = 0;
        guiTop = 0;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        minecraft.getTextureManager().bindTexture(TEXTURE);
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        blit(guiLeft + 78, guiTop + 32, 176, 81, container.getProgressionScaled(), 23);
        blit(guiLeft + 57, guiTop + 64 + container.getEnoughEnergyScaled(), 176, container.getEnoughEnergyScaled(), 14, 14 - container.getEnoughEnergyScaled());
    }
}
