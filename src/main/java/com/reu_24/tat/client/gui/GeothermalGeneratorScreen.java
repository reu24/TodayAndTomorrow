package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.EnergyContainer;
import com.reu_24.tat.container.GeothermalGeneratorContainer;
import com.reu_24.tat.util.ModResourceLocation;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GeothermalGeneratorScreen extends EnergyScreen<GeothermalGeneratorContainer> {

    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/simple_generators.png");

    public GeothermalGeneratorScreen(GeothermalGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        guiLeft = 0;
        guiTop = 0;
        xSize = 176;
        ySize = 166;
    }
    // TODO Info button
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        minecraft.getTextureManager().bindTexture(TEXTURE);
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        addButton(new ImageButton(guiLeft + 3, guiTop + 18, 19, 19, 176, 77, 20, TEXTURE, (button) -> {

        }));
        if (container.isProducing()) {
            blit(guiLeft + 82, guiTop + 34, 176, 62, 11, 15);
        }
    }
}
