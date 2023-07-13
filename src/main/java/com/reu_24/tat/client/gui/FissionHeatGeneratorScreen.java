package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.FissionHeatGeneratorContainer;
import com.reu_24.tat.util.ModResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FissionHeatGeneratorScreen extends EnergyScreen<FissionHeatGeneratorContainer> {
    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/fission_heat_generator.png");

    public FissionHeatGeneratorScreen(FissionHeatGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
    }
}
