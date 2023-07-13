package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.CoalGeneratorContainer;
import com.reu_24.tat.util.ModResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CoalGeneratorScreen extends EnergyScreen<CoalGeneratorContainer> {
    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/coal_generator.png");

    public CoalGeneratorScreen(CoalGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
        blit(guiLeft + 81, guiTop + 55 + container.getProgressionScaled(),  176, container.getProgressionScaled(), 13, 13 - container.getProgressionScaled());
    }
}
