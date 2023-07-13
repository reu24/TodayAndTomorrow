package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.ElectricFurnaceContainer;
import com.reu_24.tat.util.ModResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectricFurnaceScreen extends EnergyScreen<ElectricFurnaceContainer> {
    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/electric_furnace.png");

    public ElectricFurnaceScreen(ElectricFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
        blit(guiLeft + 79, guiTop + 36, 176, 81, container.getProgressionScaled(), 17);
        blit(guiLeft + 57, guiTop + 54 + container.getEnoughEnergyScaled(), 176, container.getEnoughEnergyScaled(), 14, 14 - container.getEnoughEnergyScaled());
    }
}
