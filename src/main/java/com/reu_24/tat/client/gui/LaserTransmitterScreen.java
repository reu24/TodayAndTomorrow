package com.reu_24.tat.client.gui;

import com.reu_24.tat.container.LaserTransmitterContainer;
import com.reu_24.tat.util.ModResourceLocation;
import com.reu_24.tat.util.TatPacketHandler;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LaserTransmitterScreen extends EnergyScreen<LaserTransmitterContainer> {

    private static final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/laser_transmitter.png");

    protected Widget alwaysOnButton = null;
    protected Widget needsRedstoneButton = null;

    public LaserTransmitterScreen(LaserTransmitterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        guiLeft = 0;
        guiTop = 0;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void init() {
        super.init();

        alwaysOnButton = addButton(new ImageButton(guiLeft + 79, guiTop + 33, 20, 19, 176, 117, 20, TEXTURE, (button) -> {
            TatPacketHandler.LASER_CHANNEL.sendToServer(new TatPacketHandler.LaserPacket(container.getTileEntity().getPos(), false));
        }));
        needsRedstoneButton = addButton(new ImageButton(guiLeft + 79, guiTop + 33, 20, 19, 176, 157, 20, TEXTURE, (button) -> {
            TatPacketHandler.LASER_CHANNEL.sendToServer(new TatPacketHandler.LaserPacket(container.getTileEntity().getPos(), true));
        }));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        minecraft.getTextureManager().bindTexture(TEXTURE);
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        boolean isAlwaysActive = container.getTileEntity().isAlwaysActive();
        alwaysOnButton.visible = isAlwaysActive;
        needsRedstoneButton.visible = !isAlwaysActive;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);

        if (isMouseOver(mouseX, mouseY, 79, 33, 98, 51)) {
            if (container.getTileEntity().isAlwaysActive()) {
                renderTooltip(new TranslationTextComponent( "screen.tat.laser.alwaysactive").getFormattedText(), mouseX, mouseY);
            } else {
                renderTooltip(new TranslationTextComponent( "screen.tat.laser.needsredstone").getFormattedText(), mouseX, mouseY);
            }
        }
    }
}
