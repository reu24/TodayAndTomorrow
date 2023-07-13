package com.reu_24.tat.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public class BaseScreen<C extends Container> extends ContainerScreen<C> {

    public BaseScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize, 256, 256);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        font.drawString(title.getFormattedText(), 8, 8, 0x404040);
        font.drawString(playerInventory.getDisplayName().getFormattedText(), 8, 69, 0x404040);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    public boolean isMouseOver(int mouseX, int mouseY, int xFrom, int yFrom, int xTo, int yTo) {
        return mouseX - guiLeft > xFrom && mouseX - guiLeft < xTo && mouseY - guiTop > yFrom && mouseY - guiTop < yTo;
    }

    @Override
    public void blit (int xTo, int yTo, int xFrom, int yFrom, int width, int height) {
        super.blit(xTo, yTo, xFrom, yFrom, width, height);
    }
}
