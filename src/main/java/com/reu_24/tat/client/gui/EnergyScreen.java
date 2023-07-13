package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.EnergyContainer;
import com.reu_24.tat.util.helper.ElectricityHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public abstract class EnergyScreen<C extends EnergyContainer> extends BaseScreen<C> {

    public EnergyScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        blit(guiLeft + 145, guiTop + 66 - container.getEnergyScaled(),  176, 62 - container.getEnergyScaled(), 12, container.getEnergyScaled());
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);

        if (isMouseOver(mouseX, mouseY, 145, 18, 156, 65)) {
            String energy = ElectricityHelper.toFE(container.getEnergy()) + "/" + ElectricityHelper.toFE(container.getMaxEnergy());
            renderTooltip(energy, mouseX, mouseY);
        }
    }
}
