package com.reu_24.tat.util;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.client.entity.render.HydrogenTNTRenderer;
import com.reu_24.tat.client.gui.*;
import com.reu_24.tat.client.tileentity.renderer.LaserTransmitterTileEntityRenderer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModEntityTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TodayAndTomorrow.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainerTypes.COAL_GENERATOR.get(), CoalGeneratorScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.ALLOY_FURNACE.get(), AlloyFurnaceScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.GEOTHERMAL_GENERATOR.get(), GeothermalGeneratorScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.SOLAR_PANEL_GENERATOR.get(), SolarPanelGeneratorBaseScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.ELECTROLYSIS_MACHINE.get(), ElectrolysisMachineScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.LASER_TRANSMITTER.get(), LaserTransmitterScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.LASER_RECEIVER.get(), LaserReceiverScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.PURIFIER.get(), PurifierScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.FISSION_CONTROLLER.get(), FissionControllerScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.FISSION_HEAT_GENERATOR.get(), FissionHeatGeneratorScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HYDROGEN_TNT.get(), HydrogenTNTRenderer::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.COPPER_LASER_TRANSMITTER.get(), LaserTransmitterTileEntityRenderer::new);
    }
}
