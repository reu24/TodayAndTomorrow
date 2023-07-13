package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.ElectrolysisMachineContainer;
import com.reu_24.tat.util.ModResourceLocation;
import com.reu_24.tat.util.helper.FluidHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ElectrolysisMachineScreen extends EnergyScreen<ElectrolysisMachineContainer> {

    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/electrolysis_machine.png");

    public ElectrolysisMachineScreen(ElectrolysisMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
        blit(guiLeft + 46, guiTop + 35, 176, 117, container.getProgressionScaled(), 17);
        blit(guiLeft + 27, guiTop + 66 - container.getWaterScaled(),  188, 62 - container.getWaterScaled(), 12, container.getWaterScaled());
        blit(guiLeft + 75, guiTop + 66 - container.getHydrogenScaled(),  200, 62 - container.getHydrogenScaled(), 12, container.getHydrogenScaled());
        blit(guiLeft + 109, guiTop + 66 - container.getOxygenScaled(),  212, 62 - container.getOxygenScaled(), 12, container.getOxygenScaled());
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        // fluid containers
        if (isMouseOver(mouseX, mouseY, 26, 18, 39, 66)) {
            String water = FluidHelper.toBucket(container.getWater()) + "/" + FluidHelper.toBucket(container.getMaxWater()) + " " + new TranslationTextComponent("fluid.tat.water").getFormattedText() +
                    "\n" + "\u00A76" + new TranslationTextComponent( "mouse.click.right").getFormattedText() + "\u00A7f" + " " + new TranslationTextComponent("screen.tat.electrolysis.water").getFormattedText();
            renderTooltip(water, mouseX, mouseY);
        }
        if (isMouseOver(mouseX, mouseY, 74, 18, 87, 66)) {
            String hydrogen = FluidHelper.toBucket(container.getHydrogen()) + "/" + FluidHelper.toBucket(container.getMaxHydrogen()) + " " + new TranslationTextComponent("fluid.tat.hydrogen").getFormattedText();
            renderTooltip(hydrogen, mouseX, mouseY);
        }
        if (isMouseOver(mouseX, mouseY, 108, 18, 121, 66)) {
            String oxygen = FluidHelper.toBucket(container.getOxygen()) + "/" + FluidHelper.toBucket(container.getMaxOxygen()) + " " + new TranslationTextComponent("fluid.tat.oxygen").getFormattedText();
            renderTooltip(oxygen, mouseX, mouseY);
        }

        // slots
        if (isMouseOver(mouseX, mouseY, 123, 21, 140, 38) && !container.hasItemInSlot(0)) {
            renderTooltip(new TranslationTextComponent( "screen.tat.electrolysis.hydrogen").getFormattedText(), mouseX, mouseY);
        }
        if (isMouseOver(mouseX, mouseY, 123, 45, 140, 62) && !container.hasItemInSlot(1)) {
            renderTooltip(new TranslationTextComponent( "screen.tat.electrolysis.oxygen").getFormattedText(), mouseX, mouseY);
        }
    }
}